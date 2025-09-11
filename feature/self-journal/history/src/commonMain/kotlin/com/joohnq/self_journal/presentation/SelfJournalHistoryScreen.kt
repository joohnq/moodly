package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalHistoryScreen(
    onNavigateEditJournaling: (Long) -> Unit,
    onGoBack: () -> Unit,
    viewModel: SelfJournalHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
