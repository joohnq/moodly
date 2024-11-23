package com.joohnq.moodapp.ui.presentation.health_journal.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.health_journal.event.HealthJournalEvent

data class HealthJournalState(
    val healthJournal: List<HealthJournalRecord>,
    val onEvent: (HealthJournalEvent) -> Unit = {},
)