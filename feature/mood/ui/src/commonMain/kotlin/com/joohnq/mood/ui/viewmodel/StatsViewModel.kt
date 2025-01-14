package com.joohnq.mood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.mood.domain.StatsException
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetStatsByDate
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatsViewModel(
    private val getStatsUseCase: GetStatsUseCase,
    private val getStatsByDate: GetStatsByDate,
    private val deleteStatsUseCase: DeleteStatsUseCase,
    private val addStatsUseCase: AddStatsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StatsState())
    val state: StateFlow<StatsState> = _state.asStateFlow()

    fun onAction(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.GetStatsRecords -> getStatsRecords()
            is StatsIntent.AddStatsRecord -> addStatsRecord(intent.statsRecord)

            StatsIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
        }
    }

    private fun getStatsRecords() =
        viewModelScope.launch {
            changeStatsRecordsStatus(UiState.Loading)
            val res = getStatsUseCase().toUiState()
            changeStatsRecordsStatus(res)
        }

    private fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch {
        changeAddingStatus(UiState.Loading)

        val statsByDate = getStatsByDate(DatetimeProvider.getCurrentDateTime().date)

        if (statsByDate.getOrNull() != null) {
            val item = Result.failure<Boolean>(StatsException.StatsAlreadyAdded)
            changeAddingStatus(item.toUiState())
            return@launch
        }

        val res = addStatsUseCase(statsRecord).toUiState()
        changeAddingStatus(res)
    }

    private fun changeStatsRecordsStatus(status: UiState<List<StatsRecord>>) {
        _state.update { it.copy(statsRecords = status) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }
}