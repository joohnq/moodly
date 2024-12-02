package com.joohnq.moodapp.ui.presentation.all_journals.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.ui.presentation.all_journals.AllJournalIntent
import com.joohnq.moodapp.ui.presentation.all_journals.AllJournalViewModelState
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.viewmodel.HealthJournalIntent

data class AllJournalState(
    val allJournalViewModelState: AllJournalViewModelState,
    val user: UiState<User>,
    val healthJournalRecords: UiState<List<HealthJournalRecord>>,
    val onAction: (HealthJournalIntent) -> Unit,
    val onAllAction: (AllJournalIntent) -> Unit,
    val onEvent: (AllJournalEvent) -> Unit
)
