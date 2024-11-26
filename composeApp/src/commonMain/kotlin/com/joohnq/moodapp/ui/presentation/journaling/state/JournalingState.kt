package com.joohnq.moodapp.ui.presentation.journaling.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.journaling.event.JournalingEvent

data class JournalingState(
    val journals: List<HealthJournalRecord>,
    val onEvent: (JournalingEvent) -> Unit = {}
)