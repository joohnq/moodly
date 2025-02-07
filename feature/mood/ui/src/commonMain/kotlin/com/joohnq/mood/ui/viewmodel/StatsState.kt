package com.joohnq.mood.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.domain.entity.MoodRecord

data class StatsState(
    val records: UiState<List<MoodRecord>> = UiState.Idle,
)