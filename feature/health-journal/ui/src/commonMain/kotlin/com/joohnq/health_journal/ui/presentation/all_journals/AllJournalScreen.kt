package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.health_journal.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModelIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.datetime.LocalDate

class AllJournalScreen(
    private val localDate: LocalDate? = null,
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

        localDate?.let {
            LaunchedEffect(Unit) {
                allJournalViewModel.onAction(AllJournalViewModelIntent.UpdateSelectedDateTime(it))
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
