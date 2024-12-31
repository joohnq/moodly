package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.Stressor

data class AddingStressLevelViewModelState(
    val stressLevel: StressLevel = StressLevel.One,
    val stressors: List<Stressor> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String? = null,
    val sliderValue: Float = 0f
)