package com.joohnq.moodapp.ui.presentation.edit_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.state.EditJournalingState
import com.joohnq.moodapp.ui.state.UiState.Companion.onSuccess
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel

class EditJournalingScreen(val id: Int) : CustomScreen<EditJournalingState>() {
    @Composable
    override fun Screen(): EditJournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: EditJournalingEvent) =
            when (event) {
                EditJournalingEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(Unit) {
            healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournalById(id))
        }

        LaunchedEffect(healthJournalState.deleting) {
            healthJournalState.deleting.onSuccess {
                onGoBack()
                healthJournalViewModel.onAction(HealthJournalIntent.ResetDeletingHeathJournal)
            }
        }

        LaunchedEffect(healthJournalState.editing.status) {
            healthJournalState.editing.status.onSuccess {
                healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
                onGoBack()
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                healthJournalViewModel.onAction(HealthJournalIntent.ResetEditingHeathJournal)
            }
        }

        return EditJournalingState(
            snackBarState = snackBarState,
            healthJournal = healthJournalState.editing.editingHealthJournalRecord,
            isEditing = healthJournalState.editing.isEditing,
            isDifferent = healthJournalState.editing.editingHealthJournalRecord.title != healthJournalState.editing.currentHealthJournalRecord.title || healthJournalState.editing.editingHealthJournalRecord.description != healthJournalState.editing.currentHealthJournalRecord.description,
            openDeleteDialog = healthJournalState.editing.openDeleteDialog,
            onAction = healthJournalViewModel::onAction,
            onEvent = ::onEvent,
        )
    }

    @Composable
    override fun UI(state: EditJournalingState) = EditJournalingUI(state)
}
