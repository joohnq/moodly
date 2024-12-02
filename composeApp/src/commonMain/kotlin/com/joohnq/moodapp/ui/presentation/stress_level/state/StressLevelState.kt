package com.joohnq.moodapp.ui.presentation.stress_level.state

import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.moodapp.ui.state.UiState

data class StressLevelState(
    val stressLevelRecords: UiState<List<StressLevelRecord>>,
    val onEvent: (StressLevelEvent) -> Unit = {}
)