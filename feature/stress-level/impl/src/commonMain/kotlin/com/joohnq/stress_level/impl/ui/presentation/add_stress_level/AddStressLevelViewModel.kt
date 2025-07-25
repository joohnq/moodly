package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import androidx.lifecycle.ViewModel
import com.joohnq.api.mapper.toggle
import com.joohnq.stress_level.impl.ui.mapper.fromSliderValueToStressLevelResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddStressLevelViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddStressLevelContract.State())
    val state: StateFlow<AddStressLevelContract.State> =
        _state.asStateFlow()

    fun onAction(intent: AddStressLevelContract.Intent) {
        when (intent) {
            is AddStressLevelContract.Intent.UpdateAddingStressors ->
                _state.update {
                    it.copy(
                        record =
                            it.record.copy(
                                stressors = state.value.record.stressors.toggle(intent.stressor)
                            )
                    )
                }

            is AddStressLevelContract.Intent.UpdateAddingSliderValue ->
                _state.update {
                    it.copy(
                        sliderValue = intent.sliderValue,
                        record = it.record.copy(
                            stressLevel = intent.sliderValue.fromSliderValueToStressLevelResource()
                        )
                    )
                }

            is AddStressLevelContract.Intent.ResetState ->
                _state.update { AddStressLevelContract.State() }
        }
    }
}