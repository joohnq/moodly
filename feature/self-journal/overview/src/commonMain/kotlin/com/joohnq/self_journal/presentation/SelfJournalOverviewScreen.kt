package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalOverviewScreen(
    onNavigateAddSelfJournal: () -> Unit,
    onNavigateToSelfJournalHistory: () -> Unit,
    onGoBack: () -> Unit,
    onEditSelfJournal: (Int) -> Unit,
    viewModel: SelfJournalOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: SelfJournalOverviewContract.Event) =
        when (event) {
            SelfJournalOverviewContract.Event.GoBack ->
                onGoBack()

            SelfJournalOverviewContract.Event.NavigateToAddSelfJournal -> onNavigateAddSelfJournal()

            SelfJournalOverviewContract.Event.NavigateToSelfJournalHistory -> onNavigateToSelfJournalHistory()

            is SelfJournalOverviewContract.Event.NavigateToEditSelfJournal ->
                onEditSelfJournal(event.id)
        }

    SelfJournalOverviewContent(
        state = state,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}