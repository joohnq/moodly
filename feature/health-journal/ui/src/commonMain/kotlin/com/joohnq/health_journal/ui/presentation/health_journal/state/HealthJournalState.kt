package com.joohnq.health_journal.ui.presentation.health_journal.state

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.mood.state.UiState

data class HealthJournalState(
    val healthJournal: UiState<List<HealthJournalRecord>>,
    val onEvent: (HealthJournalEvent) -> Unit = {},
)