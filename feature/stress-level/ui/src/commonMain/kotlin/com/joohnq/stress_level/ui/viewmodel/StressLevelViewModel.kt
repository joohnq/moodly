package com.joohnq.stress_level.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.mood.state.UiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stressLevelState = MutableStateFlow(StressLevelState())
    val stressLevelState: StateFlow<StressLevelState> = _stressLevelState.asStateFlow()

    fun onAction(intent: StressLevelIntent) {
        when (intent) {
            StressLevelIntent.GetStressLevelRecords -> getStressLevelRecords()
            is StressLevelIntent.AddStressLevelRecord -> addStressLevelRecord(intent.stressLevelRecord)

            is StressLevelIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
        }
    }

    private fun getStressLevelRecords() =
        viewModelScope.launch(dispatcher) {
            _stressLevelState.update { it.copy(stressLevelRecords = UiState.Loading) }

            try {
                val res = getStressLevelsUseCase()
                _stressLevelState.update { it.copy(stressLevelRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _stressLevelState.update { it.copy(stressLevelRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun addStressLevelRecord(stressLevelRecord: StressLevelRecord) =
        viewModelScope.launch(dispatcher) {
            changeAddingStatus(UiState.Loading)

            val res = addStressLevelUseCase(stressLevelRecord)

            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add stress level record"
                )
            )
        }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _stressLevelState.update { it.copy(adding = status) }
    }
}