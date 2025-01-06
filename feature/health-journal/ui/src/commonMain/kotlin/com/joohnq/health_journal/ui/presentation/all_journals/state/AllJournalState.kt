package com.joohnq.health_journal.ui.presentation.all_journals.state

import com.joohnq.domain.entity.User
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModelIntent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModelState
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.core.ui.entity.UiState

data class AllJournalState(
    val allJournalViewModelState: AllJournalViewModelState,
    val user: UiState<User>,
    val healthJournalRecords: UiState<List<HealthJournalRecord>>,
    val onAction: (HealthJournalIntent) -> Unit,
    val onAllAction: (AllJournalViewModelIntent) -> Unit,
    val onEvent: (AllJournalEvent) -> Unit,
)
