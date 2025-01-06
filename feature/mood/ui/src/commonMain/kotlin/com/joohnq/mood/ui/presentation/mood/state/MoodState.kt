package com.joohnq.mood.ui.presentation.mood.state

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.shared.domain.entity.UiState

data class MoodState(
    val statsRecord: StatsRecord?,
    val statsRecords: UiState<List<StatsRecord>>,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
    val onEvent: (MoodEvent) -> Unit = {},
)