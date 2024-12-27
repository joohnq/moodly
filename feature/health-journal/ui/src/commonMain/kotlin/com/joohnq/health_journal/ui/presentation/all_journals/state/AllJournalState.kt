package com.joohnq.health_journal.ui.presentation.all_journals.state

import com.joohnq.mood.state.UiState
import com.joohnq.mood.domain.HealthJournalRecord
import com.joohnq.mood.domain.User
import com.joohnq.health_journal.ui.presentation.all_journals.AllJournalIntent
import com.joohnq.health_journal.ui.presentation.all_journals.AllJournalViewModelState
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.mood.viewmodel.HealthJournalIntent

data class AllJournalState(
    val allJournalViewModelState: AllJournalViewModelState,
    val user: UiState<User>,
    val healthJournalRecords: UiState<List<HealthJournalRecord>>,
    val onAction: (HealthJournalIntent) -> Unit,
    val onAllAction: (AllJournalIntent) -> Unit,
    val onEvent: (AllJournalEvent) -> Unit
)
