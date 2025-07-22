package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.event.SelfJournalHistoryEvent
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryViewModel
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalIntent
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalViewModel

@Composable
fun SelfJournalHistoryScreen(
    onNavigateEditJournaling: (Int) -> Unit,
    onGoBack: () -> Unit,
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val selfJournalState by selfJournalViewModel.state.collectAsState()
    val historyViewModel: SelfJournalHistoryViewModel = sharedViewModel()
    val historyState by historyViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalHistoryEvent) =
        when (event) {
            SelfJournalHistoryEvent.OnGoBack -> onGoBack()
            is SelfJournalHistoryEvent.OnSelectJournalHistory -> onNavigateEditJournaling(event.id)
            SelfJournalHistoryEvent.OnDelete -> selfJournalViewModel.onAction(
                SelfJournalIntent.Delete(
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

