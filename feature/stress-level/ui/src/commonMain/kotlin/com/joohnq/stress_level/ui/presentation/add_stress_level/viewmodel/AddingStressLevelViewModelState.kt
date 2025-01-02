package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.ui.StressLevelResource
import com.joohnq.stress_level.ui.StressorResource

data class AddingStressLevelViewModelState(
    val stressLevel: StressLevelResource = StressLevelResource.One,
    val stressors: List<StressorResource> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String? = null,
    val sliderValue: Float = 0f,
)