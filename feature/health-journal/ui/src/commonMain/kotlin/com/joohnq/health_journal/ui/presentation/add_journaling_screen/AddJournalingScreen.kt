package com.joohnq.health_journal.ui.presentation.add_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.fold
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.state.AddJournalingState
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddJournalingViewModel
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddingJournalingViewModelIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.shared_resources.CustomScreen
import com.joohnq.shared_resources.sharedViewModel
import kotlinx.coroutines.launch

class AddJournalingScreen(private val onGoBack: () -> Unit) : CustomScreen<AddJournalingState>() {
    @Composable
    override fun Screen(): AddJournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val addJournalingViewModel: AddJournalingViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val addingHealthJournalState by addJournalingViewModel.state.collectAsState()
        val healthJournalState by healthJournalViewModel.state.collectAsState()

        fun onEvent(event: AddJournalingEvent) =
            when (event) {
                AddJournalingEvent.OnGoBack -> onGoBack()
                AddJournalingEvent.OnAdd ->
                    healthJournalViewModel.onAction(
                        HealthJournalIntent.AddHealthJournal(
                            HealthJournalRecord(
                                mood = addingHealthJournalState.mood!!.toDomain(),
                                title = addingHealthJournalState.title,
                                description = addingHealthJournalState.description
                            )
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
                addJournalingViewModel.onAction(AddingJournalingViewModelIntent.ResetState)
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
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AddJournalingState) = AddJournalingUI(state)
}
