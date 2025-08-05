package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    fun onEvent(event: SelfJournalHistoryContract.Event) =
        when (event) {
            SelfJournalHistoryContract.Event.OnGoBack -> onGoBack()
            is SelfJournalHistoryContract.Event.OnSelectJournalHistory ->
                onNavigateEditJournaling(event.id)
        }

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            SelfJournalHistoryContract.Intent.GetAll
        )
    }

    SelfJournalHistoryContent(
        state = state,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}