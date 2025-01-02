package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModelIntent
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.EditJournalingScreen
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel
import kotlinx.datetime.LocalDate

class AllJournalScreen(private val localDate: LocalDate? = null) : CustomScreen<AllJournalState>() {
    @Composable
    override fun Screen(): AllJournalState {
        val userViewModel: UserViewModel = sharedViewModel()
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val healthJournalState by healthJournalViewModel.state.collectAsState()
        val userState by userViewModel.state.collectAsState()
        val allJournalViewModel: AllJournalViewModel = sharedViewModel()
        val allJournalState by allJournalViewModel.allJournalViewModelState.collectAsState()

        fun onEvent(event: AllJournalEvent) =
            when (event) {
                AllJournalEvent.OnGoBack -> onGoBack()
                is AllJournalEvent.OnSelectJournal -> onNavigate(EditJournalingScreen(event.id))
                AllJournalEvent.OnDelete -> healthJournalViewModel.onAction(
                    HealthJournalIntent.DeleteHealthJournal(
                        allJournalState.currentDeleteId
                    )
                )
            }

        if (localDate != null) {
            LaunchedEffect(Unit) {
                allJournalViewModel.onAction(
                    AllJournalViewModelIntent.UpdateSelectedDateTime(
                        localDate
                    )
                )
            }
        }

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
