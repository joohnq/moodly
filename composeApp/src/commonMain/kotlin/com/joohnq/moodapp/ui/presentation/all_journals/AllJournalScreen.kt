package com.joohnq.moodapp.ui.presentation.all_journals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.ui.presentation.all_journals.state.AllJournalState
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.EditJournalingScreen
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

class AllJournalScreen : CustomScreen<AllJournalState>() {
    @Composable
    override fun Screen(): AllJournalState {
        val userViewModel: UserViewModel = sharedViewModel()
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()
        val userState by userViewModel.userState.collectAsState()
        val user = userState.user.getValue()
        val healthJournalMap =
            remember {
                StatsManager.getHealthJournalBasedOnUserEntry(
                    user.dateCreated,
                    healthJournalState.healthJournalRecords.getValue()
                )
            }
        var selectedDateTime by remember { mutableStateOf(DatetimeManager.getCurrentDateTime().date) }

        fun onEvent(event: AllJournalEvent) =
            when (event) {
                AllJournalEvent.OnGoBack -> onGoBack()
                is AllJournalEvent.OnSelectJournal -> onNavigate(EditJournalingScreen(event.id))
                is AllJournalEvent.OnSelectDate -> selectedDateTime = event.localDate
            }

        return AllJournalState(
            selectedDateTime = selectedDateTime,
            healthJournals = healthJournalMap,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AllJournalState) = AllJournalUI(state)
}
