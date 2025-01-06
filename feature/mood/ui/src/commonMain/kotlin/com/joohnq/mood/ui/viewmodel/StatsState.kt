package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.core.ui.entity.UiState

data class StatsState(
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val deleting: UiState<Boolean> = UiState.Idle,
)