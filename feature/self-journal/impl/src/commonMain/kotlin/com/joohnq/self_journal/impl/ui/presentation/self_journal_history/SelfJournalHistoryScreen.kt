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
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val selfJournalState by selfJournalViewModel.state.collectAsState()
    val historyViewModel: SelfJournalHistoryViewModel = sharedViewModel()
    val historyState by historyViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalHistoryContract.Event) =
        when (event) {
            SelfJournalHistoryContract.Event.OnGoBack -> onGoBack()
            is SelfJournalHistoryContract.Event.OnSelectJournalHistory -> onNavigateEditJournaling(
                event.id
            )

            SelfJournalHistoryContract.Event.OnDelete -> selfJournalViewModel.onAction(
                SelfJournalContract.Intent.Delete(
                    historyState.currentDeleteId
                )
            )
        }

    SelfJournalHistoryContent(
        state = historyState,
        onAction = historyViewModel::onAction,
        records = selfJournalState.records,
        onEvent = ::onEvent,
    )
}