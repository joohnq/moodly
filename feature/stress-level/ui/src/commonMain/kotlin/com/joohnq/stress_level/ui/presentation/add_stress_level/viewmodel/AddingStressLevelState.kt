package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.resource.StressorResource

data class AddingStressLevelState(
    val stressLevel: StressLevelResource = StressLevelResource.One,
    val stressors: List<StressorResource> = emptyList(),
    val otherValue: String = "",
    val otherValueError: String? = null,
    val sliderValue: Float = 0f,
)