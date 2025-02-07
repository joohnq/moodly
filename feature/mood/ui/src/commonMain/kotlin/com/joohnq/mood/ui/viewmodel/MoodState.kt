package com.joohnq.mood.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.ui.resource.MoodRecordResource

data class MoodState(
    val records: UiState<List<MoodRecordResource>> = UiState.Idle,
)