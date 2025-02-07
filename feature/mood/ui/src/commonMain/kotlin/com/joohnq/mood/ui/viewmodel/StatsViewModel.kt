package com.joohnq.mood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StatsViewModel(
    private val getStatsUseCase: GetStatsUseCase,
    private val deleteStatsUseCase: DeleteStatsUseCase,
    private val addStatsUseCase: AddStatsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StatsState())
    val state: StateFlow<StatsState> = _state.asStateFlow()

    private val _sideEffect = Channel<StatSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.GetStatsRecords -> getStatsRecords()
            is StatsIntent.AddStatsRecord -> addStatsRecord(intent.record)
            is StatsIntent.DeleteStatsRecord -> deleteStatsRecord(intent.id)
        }
    }

    private fun getStatsRecords() =
        viewModelScope.launch {
            changeStatsRecordsStatus(UiState.Loading)
            val res = getStatsUseCase().toUiState()
            changeStatsRecordsStatus(res)
        }

    private fun addStatsRecord(record: MoodRecord) = viewModelScope.launch {
        val res = addStatsUseCase(record).toUiState()
        res.onSuccess {
            _sideEffect.send(StatSideEffect.StatsAdded)
        }.onFailure {
            _sideEffect.send(StatSideEffect.ShowError(it))
        }
    }

    private fun deleteStatsRecord(id: Int) = viewModelScope.launch {
        val res = deleteStatsUseCase(id).toUiState()
        res.onSuccess {
            _sideEffect.send(StatSideEffect.StatsDeleted)
        }.onFailure {
            _sideEffect.send(StatSideEffect.ShowError(it))
        }
    }

    private fun changeStatsRecordsStatus(status: UiState<List<MoodRecord>>) {
        _state.update { it.copy(records = status) }
    }
}