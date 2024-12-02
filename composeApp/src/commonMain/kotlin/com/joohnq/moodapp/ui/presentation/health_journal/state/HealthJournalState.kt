package com.joohnq.moodapp.ui.presentation.health_journal.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.moodapp.ui.state.UiState

data class HealthJournalState(
    val healthJournal: UiState<List<HealthJournalRecord>>,
    val onEvent: (HealthJournalEvent) -> Unit = {},
)