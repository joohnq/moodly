package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SelfJournalHistoryScreen(
    onNavigateEditJournaling: (Int) -> Unit,
    onGoBack: () -> Unit,
    selfJournalViewModel: SelfJournalViewModel = sharedViewModel(),
    selfJournalHistoryViewModel: SelfJournalHistoryViewModel = sharedViewModel()
) {
    val selfJournalState by selfJournalViewModel.state.collectAsState()
    val state by selfJournalHistoryViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalHistoryContract.Event) =
        when (event) {
            SelfJournalHistoryContract.Event.OnGoBack -> onGoBack()
            is SelfJournalHistoryContract.Event.OnSelectJournalHistory ->
                onNavigateEditJournaling(
                    event.id
                )

            is SelfJournalHistoryContract.Event.OnDelete ->
                selfJournalViewModel.onIntent(
                    SelfJournalContract.Intent.Delete(
                        event.id
                    )
                )
        }

    SelfJournalHistoryContent(
        state = state,
        records = selfJournalState.records,
        onEvent = ::onEvent
    )
}