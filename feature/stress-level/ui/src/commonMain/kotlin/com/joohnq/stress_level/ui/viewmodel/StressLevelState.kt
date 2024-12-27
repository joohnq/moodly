package com.joohnq.stress_level.ui.viewmodel

import com.joohnq.mood.state.UiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord

data class StressLevelState(
    val stressLevelRecords: UiState<List<StressLevelRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
)