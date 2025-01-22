package com.joohnq.health_journal.ui.presentation.add_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddJournalingViewModel
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddingJournalingIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalSideEffect
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.mood.ui.mapper.toDomain
import kotlinx.coroutines.launch

@Composable
fun AddJournalingScreen(onGoBack: () -> Unit) {
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val addJournalingViewModel: AddJournalingViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val state by addJournalingViewModel.state.collectAsState()

    fun onError(error: Throwable) =
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }

    fun onEvent(event: AddJournalingEvent) =
        when (event) {
            AddJournalingEvent.OnGoBack -> onGoBack()
            AddJournalingEvent.OnAdd ->
                healthJournalViewModel.onAction(
                    HealthJournalIntent.AddHealthJournal(
                        HealthJournalRecord(
                            mood = state.mood!!.toDomain(),
                            title = state.title,
                            description = state.description
                        )
                    )
                )
        }

    LaunchedEffect(healthJournalViewModel) {
        scope.launch {
            healthJournalViewModel.sideEffect.collect { effect ->
                when (effect) {
                    HealthJournalSideEffect.HealthJournalAdded -> {
                        onEvent(AddJournalingEvent.OnGoBack)
                        healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
                    }

                    is HealthJournalSideEffect.ShowError -> onError(effect.error)
                    else -> Unit
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addJournalingViewModel.onAction(AddingJournalingIntent.ResetState)
        }
    }

    return AddJournalingUI(
        snackBarState = snackBarState,
        state = state,
        onAction = addJournalingViewModel::onAction,
        onEvent = ::onEvent
    )
}
