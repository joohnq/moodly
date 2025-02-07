package com.joohnq.self_journal.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.self_journal.ui.presentation.self_journal_history.event.SelfJournalHistoryEvent
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryIntent
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryViewModel
import com.joohnq.self_journal.ui.viewmodel.SelfJournalIntent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.datetime.LocalDate

@Composable
fun AllJournalScreen(
    localDate: LocalDate? = null,
    onNavigateEditJournaling: (Int) -> Unit,
    onGoBack: () -> Unit,
) {
    val userViewModel: UserViewModel = sharedViewModel()
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val selfJournalState by selfJournalViewModel.state.collectAsState()
    val userState by userViewModel.state.collectAsState()
    val selfJournalHistoryViewModel: SelfJournalHistoryViewModel = sharedViewModel()
    val allJournalState by selfJournalHistoryViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalHistoryEvent) =
        when (event) {
            SelfJournalHistoryEvent.OnGoBack -> onGoBack()
            is SelfJournalHistoryEvent.OnSelectJournalHistory -> onNavigateEditJournaling(event.id)
            SelfJournalHistoryEvent.OnDelete -> selfJournalViewModel.onAction(
                SelfJournalIntent.Delete(
                    allJournalState.currentDeleteId
                )
            )
        }

    localDate?.let {
        LaunchedEffect(Unit) {
            selfJournalHistoryViewModel.onAction(SelfJournalHistoryIntent.UpdateSelectedDateTime(it))
        }
    }

    AllJournalUI(
        state = allJournalState,
        user = userState.user,
        onAction = selfJournalHistoryViewModel::onAction,
        records = selfJournalState.records,
        onEvent = ::onEvent,
    )
}

