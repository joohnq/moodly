package com.joohnq.stress_level.impl.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.impl.ui.resource.StressorResource

sealed interface AddStressLevelIntent {
    data class UpdateAddingStressors(val stressor: StressorResource) : AddStressLevelIntent
    data class UpdateAddingSliderValue(val sliderValue: Float) : AddStressLevelIntent
    data object ResetState : AddStressLevelIntent
}