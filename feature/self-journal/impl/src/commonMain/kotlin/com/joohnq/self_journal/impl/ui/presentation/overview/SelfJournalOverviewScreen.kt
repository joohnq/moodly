package com.joohnq.self_journal.impl.ui.presentation.overview

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
            SelfJournalOverviewContract.Event.OnGoBack ->
                onGoBack()

            SelfJournalOverviewContract.Event.OnNavigateToAddSelfJournal -> onNavigateAddSelfJournal()

            is SelfJournalOverviewContract.Event.OnClick ->
                onNavigateToSelfJournalHistory()

            SelfJournalOverviewContract.Event.OnNavigateToSelfHistory -> onNavigateToSelfJournalHistory()

            is SelfJournalOverviewContract.Event.OnEdit ->
                onEditSelfJournal(event.id)

            is SelfJournalOverviewContract.Event.OnDelete ->
                viewModel.onIntent(
                    SelfJournalOverviewContract.Intent.Delete(
                        event.id
                    )
                )
        }

    SelfJournalOverviewContent(
        state = state,
        onEvent = ::onEvent
    )
}