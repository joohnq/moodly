package com.joohnq.health_journal.ui.presentation.edit_journaling_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingIntent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalSideEffect
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch

@Composable
fun EditJournalingScreen(id: Int, onGoBack: () -> Unit) {
    val scope = rememberCoroutineScope()
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val editJournalingViewModel: EditJournalingViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val healthJournalState by healthJournalViewModel.state.collectAsState()
    val state by editJournalingViewModel.state.collectAsState()

    val isDifferent by derivedStateOf {
        state.editingHealthJournalRecord.title != state.currentHealthJournalRecord.title ||
                state.editingHealthJournalRecord.description != state.currentHealthJournalRecord.description
    }
    val canSave by derivedStateOf { isDifferent && state.editingHealthJournalRecord.title.isNotBlank() && state.editingHealthJournalRecord.description.isNotBlank() }

    fun onError(error: Throwable) =
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }

    fun onEvent(event: EditJournalingEvent) =
        when (event) {
            EditJournalingEvent.OnGoBack -> onGoBack()
            EditJournalingEvent.OnSave ->
                healthJournalViewModel.onAction(
                    HealthJournalIntent.UpdateHealthJournal(
                        state.editingHealthJournalRecord
                    )
                )
        }

    LaunchedEffect(healthJournalState.healthJournalRecords) {
        healthJournalState.healthJournalRecords.onSuccess { healthJournals ->
            val item = healthJournals.find { it.id == id } ?: return@onSuccess
            editJournalingViewModel.onAction(
                EditJournalingIntent.SetEditJournaling(item)
            )
        }
    }

    LaunchedEffect(healthJournalViewModel) {
        scope.launch {
            healthJournalViewModel.sideEffect.collect { effect ->
                when (effect) {
                    HealthJournalSideEffect.HealthJournalEdited -> {
                        onEvent(EditJournalingEvent.OnGoBack)
//                            healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
                    }

                    HealthJournalSideEffect.HealthJournalDeleted -> {
                        onEvent(EditJournalingEvent.OnGoBack)
                    }

                    is HealthJournalSideEffect.ShowError -> onError(effect.error)
                    else -> Unit
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            editJournalingViewModel.onAction(EditJournalingIntent.ResetState)
        }
    }

    return EditJournalingUI(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onAction = editJournalingViewModel::onAction,
        onEvent = ::onEvent,
        onHealthJournalAction = healthJournalViewModel::onAction
    )
}
