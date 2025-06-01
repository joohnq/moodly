package com.joohnq.self_journal.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalScreen(
    onNavigateAddSelfJournal: () -> Unit,
    onNavigateToSelfJournalHistory: () -> Unit,
    onGoBack: () -> Unit,
    onEditSelfJournal: (Int) -> Unit,
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val state by selfJournalViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalContract.Event) =
        when (event) {
            SelfJournalContract.Event.GoBack -> onGoBack()
            SelfJournalContract.Event.NavigateToAddSelfJournal -> onNavigateAddSelfJournal()
            is SelfJournalContract.Event.Click -> onNavigateToSelfJournalHistory()
            SelfJournalContract.Event.OnNavigateToSelfHistory -> onNavigateToSelfJournalHistory()
            is SelfJournalContract.Event.EditSelfJournal -> onEditSelfJournal(event.id)
        }

    return SelfJournalUI(
        state = state,
        onEvent = ::onEvent
    )
}
