package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalScreen(
    onNavigateAddSelfJournal: () -> Unit,
    onNavigateToSelfJournalHistory: () -> Unit,
    onGoBack: () -> Unit,
    onEditSelfJournal: (Int) -> Unit
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val state by selfJournalViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalContract.Event) =
        when (event) {
            SelfJournalContract.Event.OnGoBack -> onGoBack()
            SelfJournalContract.Event.OnNavigateToAddSelfJournal -> onNavigateAddSelfJournal()
            is SelfJournalContract.Event.OnClick -> onNavigateToSelfJournalHistory()
            SelfJournalContract.Event.OnNavigateToSelfHistory -> onNavigateToSelfJournalHistory()
            is SelfJournalContract.Event.OnEdit -> onEditSelfJournal(event.id)
        }

    return SelfJournalContent(
        state = state,
        onEvent = ::onEvent
    )
}
