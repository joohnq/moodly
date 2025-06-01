package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.domain.mapper.toggle
import com.joohnq.stress_level.ui.resource.mapper.fromSliderValueToStressLevelResource
import com.joohnq.stress_level.ui.resource.StressorResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddStressLevelViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddStressLevelContract.State())
    val state: StateFlow<AddStressLevelContract.State> =
        _state.asStateFlow()

    fun onIntent(intent: AddStressLevelContract.Intent) {
        when (intent) {
            is AddStressLevelContract.Intent.UpdateStressors -> updateAddingStressStressors(
                intent.stressor
            )

            is AddStressLevelContract.Intent.UpdateSliderValue -> updateAddingSliderValue(
                intent.sliderValue
            )

            is AddStressLevelContract.Intent.ResetState -> resetState()
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
        _state.update { AddStressLevelContract.State() }
    }
}