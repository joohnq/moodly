package com.joohnq.moodapp.ui.presentation.add_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.state.AddJournalingState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import kotlinx.coroutines.launch

//class AddJournalingScreen : CustomScreen<AddJournalingState>() {
//    @Composable
//    override fun Screen(): AddJournalingState {
//    }
//
//    @Composable
//    override fun UI(state: AddJournalingState) = AddJournalingScreenUI(state)
//}

class AddJournalingScreen : CustomScreen<AddJournalingState>() {
    @Composable
    override fun Screen(): AddJournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: AddJournalingEvent) =
            when (event) {
                AddJournalingEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(healthJournalState.adding.status) {
            healthJournalState.adding.status.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onEvent(AddJournalingEvent.OnGoBack)
                    healthJournalViewModel.onAction(HealthJournalIntent.ResetAddingHeathJournal)
                    healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
                },
            )
        }

        return AddJournalingState(
            snackBarState = snackBarState,
            selectedMood = healthJournalState.adding.mood,
            title = healthJournalState.adding.title,
            titleError = healthJournalState.adding.titleError,
            desc = healthJournalState.adding.description,
            onAction = healthJournalViewModel::onAction,
            onNavigation = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AddJournalingState) = AddJournalingUI(state)
}
