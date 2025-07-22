package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal.event.SelfJournalEvent
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalViewModel

@Composable
fun SelfJournalScreen(
    onNavigateAddSelfJournal: () -> Unit,
    onNavigateToSelfJournalHistory: () -> Unit,
    onGoBack: () -> Unit,
    onEditSelfJournal: (Int) -> Unit,
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val state by selfJournalViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalEvent) =
        when (event) {
            SelfJournalEvent.OnGoBack -> onGoBack()
            SelfJournalEvent.OnNavigateToAddSelfJournal -> onNavigateAddSelfJournal()
            is SelfJournalEvent.OnClick -> onNavigateToSelfJournalHistory()
            SelfJournalEvent.OnNavigateToSelfHistory -> onNavigateToSelfJournalHistory()
            is SelfJournalEvent.OnEditSelfJournal -> onEditSelfJournal(event.id)
        }

    return SelfJournalUI(
        state = state,
        onEvent = ::onEvent
    )
}
