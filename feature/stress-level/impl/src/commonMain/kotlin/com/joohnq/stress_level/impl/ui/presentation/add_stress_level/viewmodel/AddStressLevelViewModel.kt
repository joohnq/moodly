package com.joohnq.stress_level.impl.ui.presentation.add_stress_level.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.api.mapper.toggle
import com.joohnq.stress_level.impl.ui.mapper.fromSliderValueToStressLevelResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddStressLevelViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddingStressLevelState())
    val state: StateFlow<AddingStressLevelState> =
        _state.asStateFlow()

    fun onAction(intent: AddStressLevelIntent) {
        when (intent) {
            is AddStressLevelIntent.UpdateAddingStressors -> updateAddingStressStressors(intent.stressor)
            is AddStressLevelIntent.UpdateAddingSliderValue -> updateAddingSliderValue(intent.sliderValue)
            is AddStressLevelIntent.ResetState -> resetState()
        }
    }


    private fun updateAddingSliderValue(value: Float) {
        _state.update {
            it.copy(
                sliderValue = value,
                record = it.record.copy(
                    stressLevel = value.fromSliderValueToStressLevelResource()
                )
            )
        }
    }

    private fun updateAddingStressStressors(stressor: StressorResource) {
        _state.update {
            val stressors = state.value.record.stressors.toggle(stressor)
            it.copy(record = it.record.copy(stressors = stressors))
        }
    }

    private fun resetState() {
        _state.update { AddingStressLevelState() }
    }
}