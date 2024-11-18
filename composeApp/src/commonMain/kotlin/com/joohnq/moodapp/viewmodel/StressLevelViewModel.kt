package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.mappers.toggle
import com.joohnq.moodapp.model.repository.StressLevelRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddingStressLevel(
    val status: UiState<Boolean> = UiState.Idle,
    val stressLevel: StressLevel = StressLevel.One,
    val stressors: List<Stressors> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String = "",
)

data class StressLevelState(
    val items: UiState<List<StressLevelRecord>> = UiState.Idle,
    val adding: AddingStressLevel = AddingStressLevel(),
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
            changeAddingStatus(UiState.Loading)

            val value = stressLevelState.value
            val res =
                stressLevelRepository.addStressLevel(
                    value.adding.stressLevel,
                    value.adding.stressors
                )


            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add stress level record"
                )
            )
        }

    fun addStressLevelRecord(
        stressLevel: StressLevel,
        stressors: List<Stressors> = listOf(Stressors.Other())
    ) =
        viewModelScope.launch(dispatcher) {
            changeAddingStatus(UiState.Loading)

            val res =
                stressLevelRepository.addStressLevel(stressLevel, stressors)

            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add stress level record"
                )
            )
        }

    fun updateAddingStressLevel(stressLevel: StressLevel) {
        _stressLevelState.update {
            it.copy(adding = it.adding.copy(stressLevel = stressLevel))
        }
    }

    fun updateAddingStressorOtherValue() {
        _stressLevelState.update { state ->
            val updatedStressors = state.adding.stressors.map { stressor ->
                if (stressor is Stressors.Other) stressor.copy(other = stressLevelState.value.adding.otherValue) else stressor
            }

            state.copy(adding = state.adding.copy(stressors = updatedStressors))
        }
    }

    fun updateAddingStressStressors(stressor: Stressors) {
        val list = stressLevelState.value.adding.stressors

        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    stressors = list.toggle(stressor)
                )
            )
        }
    }


    fun updateOtherValue(otherValue: String) {
        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    otherValue = otherValue
                )
            )
        }
    }

    fun updateOtherValueError(otherValueError: String) {
        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    otherValueError = otherValueError
                )
            )
        }
    }

    fun resetAddingStressLevel() {
        _stressLevelState.update {
            it.copy(
                adding = AddingStressLevel(),
            )
        }
    }


    private fun changeAddingStatus(status: UiState<Boolean>) {
        _stressLevelState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}