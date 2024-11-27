package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.StatsRepository
import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.util.helper.StatsManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StatsState(
    val freudScore: FreudScore = FreudScore.init(),
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
)

sealed class StatsIntent {
    data object GetStatsRecords : StatsIntent()
    data class AddStatsRecord(val statsRecord: StatsRecord) : StatsIntent()
    data object ResetAddingStatus : StatsIntent()
}

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _statsState = MutableStateFlow(StatsState())
    val statsState: StateFlow<StatsState> = _statsState.asStateFlow()

    fun onAction(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.GetStatsRecords -> getStatsRecords()
            is StatsIntent.AddStatsRecord -> addStatsRecord(intent.statsRecord)

            StatsIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
        }
    }

    private fun getStatsRecords() =
        viewModelScope.launch(dispatcher) {
            _statsState.update { it.copy(statsRecords = UiState.Loading) }

            try {
                val res = statsRepository.getStats()
                _statsState.update { it.copy(statsRecords = UiState.Success(res)) }
                getFreudScore(res)
            } catch (e: Exception) {
                _statsState.update { it.copy(statsRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val res = statsRepository.addStats(statsRecord.mood, statsRecord.description)

        changeAddingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Fail to add stats record"
            )
        )
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _statsState.update {
            it.copy(
                freudScore = StatsManager.getFreudScore(statsRecords)
            )
        }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _statsState.update { it.copy(adding = status) }
    }
}