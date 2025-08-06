package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalHistoryScreen(
    onNavigateEditJournaling: (Int) -> Unit,
    onGoBack: () -> Unit,
    viewModel: SelfJournalHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: SelfJournalHistoryContract.Event) {
        when (event) {
            SelfJournalHistoryContract.Event.GoBack -> onGoBack()
            is SelfJournalHistoryContract.Event.NavigateToEditSelfJournal ->
                onNavigateEditJournaling(event.id)
        }
    }

    SelfJournalHistoryContent(
        state = state,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
