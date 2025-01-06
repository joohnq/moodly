package com.joohnq.stress_level.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StressLevelState())
    val state: StateFlow<StressLevelState> = _state.asStateFlow()

    fun onAction(intent: StressLevelIntent) {
        when (intent) {
            StressLevelIntent.GetStressLevelRecords -> getStressLevelRecords()
            is StressLevelIntent.AddStressLevelRecord -> addStressLevelRecord(intent.stressLevelRecord)

            is StressLevelIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
        }
    }

    private fun getStressLevelRecords() =
        viewModelScope.launch {
            changeStressLevelRecordsStatus(UiState.Loading)
            val res = getStressLevelsUseCase().toUiState()
            changeStressLevelRecordsStatus(res)
        }

    private fun addStressLevelRecord(stressLevelRecord: StressLevelRecord) =
        viewModelScope.launch {
            changeAddingStatus(UiState.Loading)
            val res = addStressLevelUseCase(stressLevelRecord).toUiState()
            changeAddingStatus(res)
        }

    private fun changeStressLevelRecordsStatus(status: UiState<List<StressLevelRecord>>) {
        _state.update { it.copy(stressLevelRecords = status) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }
}