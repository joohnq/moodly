package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.model.entities.FreudScore
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoodsViewModel(
    private val statsRecordDAO: StatsRecordDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _freudScore:
            MutableStateFlow<FreudScore> = MutableStateFlow(FreudScore.init())
    val freudScore: MutableStateFlow<FreudScore> = _freudScore

    private val _healthJournal:
            MutableStateFlow<List<StatsRecord?>> = MutableStateFlow(listOf())
    val healthJournal: MutableStateFlow<List<StatsRecord?>> = _healthJournal

    private val _statsRecords:
            MutableStateFlow<UiState<List<StatsRecord>>> = MutableStateFlow(UiState.Idle)
    val statsRecords: MutableStateFlow<UiState<List<StatsRecord>>> = _statsRecords

    /*
    * Get all moods from database
    * Tested
    * */
    fun getMoods() = viewModelScope.launch(ioDispatcher) {
        _statsRecords.value = UiState.Loading
        statsRecordDAO.getMoods().catch {
            _statsRecords.value = UiState.Error(it.message.toString())
        }.collect {
            _statsRecords.value = UiState.Success(it)
            getFreudScore(it)
            getHealthJournal(it)
        }
    }

    private fun getFreudScore(statsRecords: List<StatsRecord>) {
        _freudScore.value = MoodsManager.getFreudScore(statsRecords)
    }

    private fun getHealthJournal(statsRecords: List<StatsRecord>) {
        _healthJournal.value = MoodsManager.getHealthJournal(statsRecords)
    }
}