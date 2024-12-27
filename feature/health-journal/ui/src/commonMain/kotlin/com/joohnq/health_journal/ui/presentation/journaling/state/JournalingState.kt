package com.joohnq.health_journal.ui.presentation.journaling.state

import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.mood.domain.HealthJournalRecord

data class JournalingState(
    val journals: List<HealthJournalRecord>,
    val onEvent: (JournalingEvent) -> Unit = {}
)