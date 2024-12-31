package com.joohnq.health_journal.ui.presentation.journaling.state

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent

data class JournalingState(
    val journals: List<HealthJournalRecord>,
    val onEvent: (JournalingEvent) -> Unit = {}
)