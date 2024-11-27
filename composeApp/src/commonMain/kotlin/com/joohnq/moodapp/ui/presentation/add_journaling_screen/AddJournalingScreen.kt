package com.joohnq.moodapp.ui.presentation.add_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.state.AddJournalingState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.AddJournalingViewModel
import com.joohnq.moodapp.viewmodel.AddingJournalingIntent
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import kotlinx.coroutines.launch

class AddJournalingScreen : CustomScreen<AddJournalingState>() {
    @Composable
    override fun Screen(): AddJournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val addJournalingViewModel: AddJournalingViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val addingHealthJournalState by addJournalingViewModel.addingJournalingState.collectAsState()
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: AddJournalingEvent) =
            when (event) {
                AddJournalingEvent.OnGoBack -> onGoBack()
                AddJournalingEvent.OnAdd ->
                    healthJournalViewModel.onAction(
                        HealthJournalIntent.AddHealthJournal(
                            HealthJournalRecord.Builder()
                                .setMood(addingHealthJournalState.mood!!)
                                .setTitle(addingHealthJournalState.title)
                                .setDescription(addingHealthJournalState.description)
                                .setStressLevel(StressLevel.fromSliderValue(addingHealthJournalState.sliderValue))
                                .setStressors(addingHealthJournalState.selectedStressStressors)
                                .build()
                        )
                    )
            }

        LaunchedEffect(healthJournalState.adding) {
            healthJournalState.adding.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onEvent(AddJournalingEvent.OnGoBack)
                    healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
                },
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                addJournalingViewModel.onAction(AddingJournalingIntent.ResetState)
                healthJournalViewModel.onAction(HealthJournalIntent.ResetAddingState)
            }
        }

        return AddJournalingState(
            snackBarState = snackBarState,
            selectedMood = addingHealthJournalState.mood,
            title = addingHealthJournalState.title,
            titleError = addingHealthJournalState.titleError,
            desc = addingHealthJournalState.description,
            onAddingAction = addJournalingViewModel::onAction,
            sliderValue = addingHealthJournalState.sliderValue,
            selectedStressStressors = addingHealthJournalState.selectedStressStressors,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AddJournalingState) = AddJournalingUI(state)
}
