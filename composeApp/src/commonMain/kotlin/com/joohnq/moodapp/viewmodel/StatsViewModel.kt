package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.data.repository.StatsRepository
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddingStats(
    val status: UiState<Boolean> = UiState.Idle,
    val mood: Mood = Mood.Depressed,
    val description: String = "",
)

data class StatsState(
    val freudScore: FreudScore = FreudScore.init(),
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val adding: AddingStats = AddingStats(),
)

sealed class StatsIntent {
    data object GetStatsRecord : StatsIntent()
    data class AddStatsRecord(val statsRecord: StatsRecord? = null) : StatsIntent()
    data class UpdateAddingStatsRecordMood(val mood: Mood) : StatsIntent()
    data class UpdateAddingStatsRecordDescription(val description: String) : StatsIntent()
    data object ResetAdding : StatsIntent()
}

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _statsState = MutableStateFlow(StatsState())
    val statsState: StateFlow<StatsState> = _statsState.asStateFlow()

    fun onAction(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.GetStatsRecord -> getStatsRecord()
            is StatsIntent.AddStatsRecord -> if (intent.statsRecord != null) addStatsRecord(intent.statsRecord) else addStatsRecord()
            is StatsIntent.UpdateAddingStatsRecordMood -> updateAddingStatsRecordMood(intent.mood)
            is StatsIntent.UpdateAddingStatsRecordDescription -> updateAddingStatsRecordDescription(
                intent.description
            )

            StatsIntent.ResetAdding -> resetAdding()
        }
    }

    private fun getStatsRecord() =
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

    private fun addStatsRecord() = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val value = statsState.value
        val res = statsRepository.addStats(value.adding.mood, value.adding.description)

        changeAddingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Fail to add stats record"
            )
        )
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


    private fun updateAddingStatsRecordMood(mood: Mood) {
        _statsState.update { it.copy(adding = it.adding.copy(mood = mood)) }
    }

    private fun updateAddingStatsRecordDescription(desc: String) {
        _statsState.update { it.copy(adding = it.adding.copy(description = desc)) }
    }

    private fun resetAdding() {
        _statsState.update { it.copy(adding = AddingStats()) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _statsState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}