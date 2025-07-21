package com.joohnq.mood.impl.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.mood.ui.resource.MoodRecordResource

data class MoodState(
    val records: UiState<List<MoodRecordResource>> = UiState.Idle,
)