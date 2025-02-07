package com.joohnq.stress_level.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

data class StressLevelState(
    val records: UiState<List<StressLevelRecordResource>> = UiState.Idle,
)