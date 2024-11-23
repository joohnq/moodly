package com.joohnq.moodapp.ui.presentation.mood.state

import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.ui.presentation.mood.event.MoodEvent

data class MoodState(
    val statsRecord: StatsRecord,
    val statsRecords: List<StatsRecord>,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
    val onEvent: (MoodEvent) -> Unit = {},
)