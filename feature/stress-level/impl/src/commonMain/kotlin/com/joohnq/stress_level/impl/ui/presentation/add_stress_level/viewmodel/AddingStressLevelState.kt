package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

data class AddingStressLevelState(
    val record: StressLevelRecordResource = StressLevelRecordResource(),
    val sliderValue: Float = 0f,
)