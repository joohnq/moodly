package com.joohnq.moodapp.ui.presentation.add_stress_level

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.util.mappers.toggle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddingStressLevelViewModelState(
    val stressLevel: StressLevel = StressLevel.One,
    val stressors: List<Stressor> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String? = null,
    val sliderValue: Float = 0f
)

sealed class AddStressLevelIntent {
    data class UpdateAddingStressors(val stressor: Stressor) : AddStressLevelIntent()
    data class UpdateAddingOtherValue(val value: String) : AddStressLevelIntent()
    data class UpdateAddingOtherValueError(val error: String?) : AddStressLevelIntent()
    data class UpdateAddingSliderValue(val sliderValue: Float) : AddStressLevelIntent()
    data object ResetState : AddStressLevelIntent()
}

class AddStressLevelViewModel : ViewModel() {
    private val _addStressLevelState = MutableStateFlow(AddingStressLevelViewModelState())
    val addStressLevelState: StateFlow<AddingStressLevelViewModelState> =
        _addStressLevelState.asStateFlow()

    fun onAction(intent: AddStressLevelIntent) {
        when (intent) {
            is AddStressLevelIntent.UpdateAddingOtherValue -> updateAddingOtherValue(intent.value)
            is AddStressLevelIntent.UpdateAddingOtherValueError -> updateAddingOtherValueError(
                intent.error
            )

            is AddStressLevelIntent.UpdateAddingStressors -> updateAddingStressStressors(intent.stressor)
            is AddStressLevelIntent.UpdateAddingSliderValue -> updateAddingSliderValue(intent.sliderValue)
            is AddStressLevelIntent.ResetState -> resetState()
        }
    }

    private fun updateAddingSliderValue(value: Float) {
        _addStressLevelState.update {
            it.copy(
                sliderValue = value, stressLevel = StressLevel.fromSliderValue(
                    value
                )
            )
        }
    }

    private fun updateAddingStressStressors(stressor: Stressor) {
        _addStressLevelState.update {
            it.copy(stressors = addStressLevelState.value.stressors.toggle(stressor))
        }
    }

    private fun updateAddingOtherValue(otherValue: String) {
        _addStressLevelState.update {
            it.copy(otherValue = otherValue)
        }
    }

    private fun updateAddingOtherValueError(otherValueError: String?) {
        _addStressLevelState.update {
            it.copy(otherValueError = otherValueError)
        }
    }

    private fun resetState() {
        _addStressLevelState.update { AddingStressLevelViewModelState() }
    }
}