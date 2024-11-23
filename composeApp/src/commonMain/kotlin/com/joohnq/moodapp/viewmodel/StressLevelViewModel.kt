package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.util.mappers.toggle
import com.joohnq.moodapp.data.repository.StressLevelRepository
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddingStressLevel(
    val status: UiState<Boolean> = UiState.Idle,
    val stressLevel: StressLevel = StressLevel.One,
    val stressors: List<Stressor> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String? = null,
    val sliderValue: Float = 0f
)

data class StressLevelState(
    val stressLevelRecords: UiState<List<StressLevelRecord>> = UiState.Idle,
    val adding: AddingStressLevel = AddingStressLevel(),
)

sealed class StressLevelIntent {
    data object GetStressLevelRecords : StressLevelIntent()
    data class AddStressLevelRecord(
        val stressLevel: StressLevel? = null,
        val stressors: List<Stressor>? = null
    ) : StressLevelIntent()

    data class UpdateAddingStatus(val status: UiState<Boolean>) : StressLevelIntent()
    data object UpdateAddingStressLevel : StressLevelIntent()
    data class UpdateAddingStressors(val stressor: Stressor) : StressLevelIntent()
    data class UpdateAddingOtherValue(val value: String) : StressLevelIntent()
    data class UpdateAddingOtherValueError(val error: String?) : StressLevelIntent()
    data class UpdateAddingSliderValue(val sliderValue: Float) : StressLevelIntent()
    data object ResetAdding : StressLevelIntent()
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
            is StressLevelIntent.AddStressLevelRecord -> if (intent.stressLevel != null && intent.stressors != null) addStressLevelRecord(
                stressLevel = intent.stressLevel,
                stressors = intent.stressors
            ) else addStressLevelRecord()

            is StressLevelIntent.UpdateAddingStatus -> updateAddingStatus(intent.status)
            is StressLevelIntent.UpdateAddingOtherValue -> updateAddingOtherValue(intent.value)
            is StressLevelIntent.UpdateAddingOtherValueError -> updateAddingOtherValueError(intent.error)
            is StressLevelIntent.UpdateAddingStressLevel -> updateAddingStressLevel()
            is StressLevelIntent.UpdateAddingStressors -> updateAddingStressStressors(intent.stressor)
            is StressLevelIntent.UpdateAddingSliderValue -> updateAddingSliderValue(intent.sliderValue)
            is StressLevelIntent.ResetAdding -> resetAdding()
        }
    }

    private fun updateAddingSliderValue(value: Float) {
        _stressLevelState.update {
            it.copy(adding = it.adding.copy(sliderValue = value))
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

    private fun addStressLevelRecord() =
        viewModelScope.launch(dispatcher) {
            updateAddingStatus(UiState.Loading)

            val value = stressLevelState.value
            val res =
                stressLevelRepository.addStressLevel(
                    value.adding.stressLevel,
                    value.adding.stressors
                )


            updateAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add stress level record"
                )
            )
        }

    private fun addStressLevelRecord(
        stressLevel: StressLevel,
        stressors: List<Stressor> = listOf(Stressor.Other())
    ) = viewModelScope.launch(dispatcher) {
        updateAddingStatus(UiState.Loading)

        val res =
            stressLevelRepository.addStressLevel(stressLevel, stressors)

        updateAddingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Fail to add stress level record"
            )
        )
    }

    private fun updateAddingStressLevel() {
        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    stressLevel = StressLevel.fromSliderValue(
                        stressLevelState.value.adding.sliderValue
                    )
                )
            )
        }
    }

    private fun updateAddingStressStressors(stressor: Stressor) {
        val list = stressLevelState.value.adding.stressors

        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    stressors = list.toggle(stressor)
                )
            )
        }
    }

    private fun updateAddingOtherValue(otherValue: String) {
        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    otherValue = otherValue
                )
            )
        }
    }

    private fun updateAddingOtherValueError(otherValueError: String?) {
        _stressLevelState.update {
            it.copy(
                adding = it.adding.copy(
                    otherValueError = otherValueError
                )
            )
        }
    }

    private fun resetAdding() {
        _stressLevelState.update {
            it.copy(
                adding = AddingStressLevel(),
            )
        }
    }

    private fun updateAddingStatus(status: UiState<Boolean>) {
        _stressLevelState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}