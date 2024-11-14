package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.model.repository.StressLevelRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StressLevelState(
    val items: UiState<List<StressLevelRecord>> = UiState.Idle,
    val addingStatus: UiState<Boolean> = UiState.Idle,
    val addingStressLevel: StressLevel = StressLevel.One,
    val addingStressors: List<Stressors> = emptyList()
)

class StressLevelViewModel(
    private val stressLevelRepository: StressLevelRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stressLevelState = MutableStateFlow(StressLevelState())
    val stressLevelState: StateFlow<StressLevelState> = _stressLevelState.asStateFlow()

    fun getStressLevelRecords() {
        viewModelScope.launch(dispatcher) {
            _stressLevelState.update { it.copy(items = UiState.Loading) }

            try {
                val res = stressLevelRepository.getStressLevels()
                _stressLevelState.update { it.copy(items = UiState.Success(res)) }
            } catch (e: Exception) {
                _stressLevelState.update { it.copy(items = UiState.Error(e.message.toString())) }
            }
        }
    }

    fun addStressLevelRecord() =
        viewModelScope.launch(dispatcher) {
            _stressLevelState.update { it.copy(addingStatus = UiState.Loading) }

            val value = stressLevelState.value
            val res =
                stressLevelRepository.addStressLevel(value.addingStressLevel, value.addingStressors)

            _stressLevelState.update {
                it.copy(
                    addingStatus =
                    if (res) UiState.Success(true) else UiState.Error(
                        "Fail to add stress level record"
                    )
                )
            }
        }

    fun addStressLevelRecord(
        stressLevel: StressLevel,
        stressors: List<Stressors> = listOf(Stressors.Other())
    ) =
        viewModelScope.launch(dispatcher) {
            _stressLevelState.update { it.copy(addingStatus = UiState.Loading) }

            val value = stressLevelState.value
            val res =
                stressLevelRepository.addStressLevel(stressLevel, stressors)

            _stressLevelState.update {
                it.copy(
                    addingStatus =
                    if (res) UiState.Success(true) else UiState.Error(
                        "Fail to add stress level record"
                    )
                )
            }
        }

    fun updateAddingStressLevel(stressLevel: StressLevel) {
        _stressLevelState.update {
            it.copy(addingStressLevel = stressLevel)
        }
    }

    fun updateAddingStressorOtherValue(otherValue: String) {
        _stressLevelState.update { currentState ->
            val updatedStressors = currentState.addingStressors.map { stressor ->
                if (stressor is Stressors.Other) stressor.copy(other = otherValue) else stressor
            }

            currentState.copy(addingStressors = updatedStressors)
        }
    }

    fun updateAddingStressStressors(stressor: Stressors) {
        val list = stressLevelState.value.addingStressors

        _stressLevelState.update {
            it.copy(
                addingStressors = if (list.contains(
                        stressor
                    )
                ) list.minus(
                    stressor
                ) else
                    list.plus(stressor)
            )
        }
    }

    fun resetAddingStressLevel() {
        _stressLevelState.update {
            it.copy(
                addingStatus = UiState.Idle,
                addingStressLevel = StressLevel.One,
                addingStressors = emptyList()
            )
        }
    }

    fun resetAddingStatus() {
        _stressLevelState.update { it.copy(addingStatus = UiState.Idle) }
    }
}