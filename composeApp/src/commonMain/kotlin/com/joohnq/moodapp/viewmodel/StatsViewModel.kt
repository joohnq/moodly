package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.model.repository.StatsRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StatsState(
    val freudScore: FreudScore = FreudScore.init(),
    val healthJournal: List<StatsRecord?> = listOf(),
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val addingStatus: UiState<Boolean> = UiState.Idle
)

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _statsState = MutableStateFlow(StatsState())
    val statsState: StateFlow<StatsState> = _statsState.asStateFlow()

    fun getStats() =
        viewModelScope.launch(dispatcher) {
            _statsState.update { it.copy(statsRecords = UiState.Loading) }

            try {
                val res = statsRepository.getStats()
                _statsState.update {
                    it.copy(
                        statsRecords = UiState.Success(res),
                    )
                }
                getFreudScore(res)
                getHealthJournal(res)
            } catch (e: Exception) {
                _statsState.update {
                    it.copy(
                        statsRecords = UiState.Error(e.message.toString()),
                    )
                }
            }
        }

    fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch(dispatcher) {
        _statsState.update { it.copy(addingStatus = UiState.Loading) }
        val res = statsRepository.addStats(statsRecord)

        _statsState.update {
            it.copy(
                addingStatus = if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _statsState.update {
            it.copy(
                freudScore = MoodsManager.getFreudScore(statsRecords)
            )
        }
    }

    private fun getHealthJournal(statsRecords: List<StatsRecord>) {
        _statsState.update {
            it.copy(
                healthJournal = MoodsManager.getHealthJournal(statsRecords = statsRecords)
            )
        }
    }
}