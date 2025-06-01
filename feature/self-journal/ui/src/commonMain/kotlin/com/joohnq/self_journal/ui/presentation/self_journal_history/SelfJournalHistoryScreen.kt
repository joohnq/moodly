package com.joohnq.self_journal.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryContract
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryViewModel
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalViewModel
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
            SelfJournalHistoryContract.Event.GoBack -> onGoBack()
            is SelfJournalHistoryContract.Event.SelectJournalHistory -> onNavigateEditJournaling(
                event.id
            )

            SelfJournalHistoryContract.Event.Delete -> selfJournalViewModel.onIntent(
                SelfJournalContract.Intent.Delete(
                    historyState.currentDeleteId
                )
            )
        }

    SelfJournalHistoryUI(
        state = historyState,
        onIntent = historyViewModel::onIntent,
        records = selfJournalState.records,
        onEvent = ::onEvent,
    )
}

