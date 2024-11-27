package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.StressLevelRepository
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StressLevelState(
    val stressLevelRecords: UiState<List<StressLevelRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
)

sealed class StressLevelIntent {
    data object GetStressLevelRecords : StressLevelIntent()
    data class AddStressLevelRecord(val stressLevelRecord: StressLevelRecord) : StressLevelIntent()

    data object ResetAddingStatus : StressLevelIntent()
}

class StressLevelViewModel(
    private val stressLevelRepository: StressLevelRepository,
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
                val res = stressLevelRepository.getStressLevels()
                _stressLevelState.update { it.copy(stressLevelRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _stressLevelState.update { it.copy(stressLevelRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun addStressLevelRecord(stressLevelRecord: StressLevelRecord) =
        viewModelScope.launch(dispatcher) {
            changeAddingStatus(UiState.Loading)

            val res =
                stressLevelRepository.addStressLevel(stressLevelRecord)

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