package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.shared_resources.CustomScreen
import com.joohnq.shared_resources.sharedViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel

class AllJournalScreen(
    private val onNavigateEditJournaling: (Int) -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<AllJournalState>() {
    @Composable
    override fun Screen(): AllJournalState {
        val userViewModel: UserViewModel = sharedViewModel()
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val healthJournalState by healthJournalViewModel.state.collectAsState()
        val userState by userViewModel.state.collectAsState()
        val allJournalViewModel: AllJournalViewModel = sharedViewModel()
        val allJournalState by allJournalViewModel.state.collectAsState()

        fun onEvent(event: AllJournalEvent) =
            when (event) {
                AllJournalEvent.OnGoBack -> onGoBack()
                is AllJournalEvent.OnSelectJournal -> onNavigateEditJournaling(event.id)
                AllJournalEvent.OnDelete -> healthJournalViewModel.onAction(
                    HealthJournalIntent.DeleteHealthJournal(
                        allJournalState.currentDeleteId
                    )
                )
            }

//        if (localDate != null) {
//            LaunchedEffect(Unit) {
//                allJournalViewModel.onAction(
//                    AllJournalViewModelIntent.UpdateSelectedDateTime(localDate)
//                )
//            }
//        }

        DisposableEffect(Unit) {
            onDispose {
                healthJournalViewModel.onAction(HealthJournalIntent.ResetDeletingStatus)
            }
        }

        return AllJournalState(
            allJournalViewModelState = allJournalState,
            user = userState.user,
            onAllAction = allJournalViewModel::onAction,
            healthJournalRecords = healthJournalState.healthJournalRecords,
            onAction = healthJournalViewModel::onAction,
            onEvent = ::onEvent,
        )
    }

    @Composable
    override fun UI(state: AllJournalState) = AllJournalUI(state)
}
