package com.joohnq.moodapp.ui.presentation.stress_level.state

import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.ui.presentation.stress_level.event.StressLevelEvent

data class StressLevelState(
    val stressLevelRecords: List<StressLevelRecord>,
    val onEvent: (StressLevelEvent) -> Unit = {}
)