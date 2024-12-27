package com.joohnq.mood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.use_case.AddStatsUseCase
import com.joohnq.domain.use_case.DeleteStatsUseCase
import com.joohnq.domain.use_case.GetStatsUseCase
import com.joohnq.mood.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatsViewModel(
    private val getStatsUseCase: GetStatsUseCase,
    private val deleteStatsUseCase: DeleteStatsUseCase,
    private val addStatsUseCase: AddStatsUseCase,
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
                val res = getStatsUseCase()
                _statsState.update { it.copy(statsRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _statsState.update { it.copy(statsRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun addStatsRecord(statsRecord: StatsRecord) = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val res = addStatsUseCase(statsRecord)

        changeAddingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Fail to add stats record"
            )
        )
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _statsState.update { it.copy(adding = status) }
    }
}