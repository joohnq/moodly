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
import kotlinx.coroutines.launch

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _freudScore = MutableStateFlow(FreudScore.init())
    val freudScore: StateFlow<FreudScore> = _freudScore.asStateFlow()

    private val _healthJournal = MutableStateFlow(emptyMap<String, List<StatsRecord>?>())
    val healthJournal: StateFlow<Map<String, List<StatsRecord>?>> = _healthJournal.asStateFlow()

    private val _statsRecords = MutableStateFlow<UiState<List<StatsRecord>>>(UiState.Idle)
    val statsRecords: StateFlow<UiState<List<StatsRecord>>> = _statsRecords.asStateFlow()

    private val _addingStatus = MutableStateFlow<UiState<Boolean>>(UiState.Idle)
    val addingStatus: StateFlow<UiState<Boolean>> = _addingStatus.asStateFlow()

    fun getStats() =
        viewModelScope.launch(dispatcher) {
            _statsRecords.value = UiState.Loading

            try {
                val res = statsRepository.getStats()
                _statsRecords.value = UiState.Success(res)
                getFreudScore(res)
                getHealthJournal(res)
            } catch (e: Exception) {
                _statsRecords.value = UiState.Error(e.message.toString())
            }
        }

    fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch(dispatcher) {
        _addingStatus.value = UiState.Loading
        val res = statsRepository.addStats(statsRecord)

        _addingStatus.value = if (res) UiState.Success(true) else UiState.Error(
            "Fail to add sleep quality record"
        )
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _freudScore.value = MoodsManager.getFreudScore(statsRecords)
    }

    private fun getHealthJournal(statsRecords: List<StatsRecord>) {
        _healthJournal.value = MoodsManager.getHealthJournal(statsRecords = statsRecords)
    }

    fun resetAddingStatus() {
        _addingStatus.value = UiState.Idle
    }
}