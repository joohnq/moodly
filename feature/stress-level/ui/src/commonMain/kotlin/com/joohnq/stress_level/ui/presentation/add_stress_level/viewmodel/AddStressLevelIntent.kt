package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.domain.entity.Stressor

sealed class AddStressLevelIntent {
    data class UpdateAddingStressors(val stressor: Stressor) : AddStressLevelIntent()
    data class UpdateAddingOtherValue(val value: String) : AddStressLevelIntent()
    data class UpdateAddingOtherValueError(val error: String?) : AddStressLevelIntent()
    data class UpdateAddingSliderValue(val sliderValue: Float) : AddStressLevelIntent()
    data object ResetState : AddStressLevelIntent()
}