package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.StatsManager
import com.joohnq.moodapp.model.repository.StatsRepository
import com.joohnq.moodapp.view.state.UiState
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
    val healthJournal: Map<String, List<StatsRecord>?> = emptyMap(),
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val adding: AddingStats = AddingStats(),
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
                _statsState.update { it.copy(statsRecords = UiState.Success(res)) }
                getFreudScore(res)
                getHealthJournal(res)
            } catch (e: Exception) {
                _statsState.update { it.copy(statsRecords = UiState.Error(e.message.toString())) }
            }
        }

    fun addStatsRecord() = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val value = statsState.value
        val res = statsRepository.addStats(value.adding.mood, value.adding.description)

        changeAddingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Fail to add stats record"
            )
        )
    }

    fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch(dispatcher) {
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

    private fun getHealthJournal(statsRecords: List<StatsRecord>) {
        _statsState.update {
            it.copy(
                healthJournal = StatsManager.getHealthJournal(statsRecords = statsRecords)
            )
        }
    }

    fun updateAddingStatsRecordMood(mood: Mood) {
        _statsState.update { it.copy(adding = it.adding.copy(mood = mood)) }
    }

    fun updateAddingStatsRecordDescription(desc: String) {
        _statsState.update { it.copy(adding = it.adding.copy(description = desc)) }
    }

    fun resetAddingStatsRecord() {
        _statsState.update { it.copy(adding = AddingStats()) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _statsState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}