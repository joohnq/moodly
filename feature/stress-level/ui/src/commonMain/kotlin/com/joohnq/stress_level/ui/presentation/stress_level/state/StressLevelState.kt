package com.joohnq.stress_level.ui.presentation.stress_level.state

import com.joohnq.shared.ui.state.UiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent

data class StressLevelState(
    val stressLevelRecords: UiState<List<StressLevelRecord>>,
    val onEvent: (StressLevelEvent) -> Unit = {},
)