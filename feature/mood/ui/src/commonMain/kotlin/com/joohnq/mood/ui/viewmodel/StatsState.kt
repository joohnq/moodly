package com.joohnq.mood.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.domain.entity.StatsRecord

data class StatsState(
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
)