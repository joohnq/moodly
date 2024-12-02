package com.joohnq.moodapp.ui.presentation.mood.state

import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.ui.presentation.mood.event.MoodEvent
import com.joohnq.moodapp.ui.state.UiState

data class MoodState(
    val statsRecord: StatsRecord?,
    val statsRecords: UiState<List<StatsRecord>>,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
    val onEvent: (MoodEvent) -> Unit = {},
)