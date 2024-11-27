package com.joohnq.moodapp.ui.presentation.edit_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.state.EditJournalingState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.ui.state.UiState.Companion.onSuccess
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel

class EditJournalingScreen(val id: Int) : CustomScreen<EditJournalingState>() {
    @Composable
    override fun Screen(): EditJournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val editJournalingViewModel: EditJournalingViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()
        val editingHealthJournalState by editJournalingViewModel.editJournalingState.collectAsState()
        val healthJournals = healthJournalState.healthJournalRecords.getValue()
        val isDifferent by derivedStateOf {
            editingHealthJournalState.editingHealthJournalRecord.title != editingHealthJournalState.currentHealthJournalRecord.title ||
                    editingHealthJournalState.editingHealthJournalRecord.description != editingHealthJournalState.currentHealthJournalRecord.description
        }
        val canSave by derivedStateOf { isDifferent && editingHealthJournalState.editingHealthJournalRecord.title.isNotBlank() && editingHealthJournalState.editingHealthJournalRecord.description.isNotBlank() }

        fun onEvent(event: EditJournalingEvent) =
            when (event) {
                EditJournalingEvent.OnGoBack -> onGoBack()
                EditJournalingEvent.OnSave ->
                    healthJournalViewModel.onAction(
                        HealthJournalIntent.UpdateHealthJournal(
                            editingHealthJournalState.editingHealthJournalRecord
                        )
                    )
            }

        LaunchedEffect(Unit) {
            editJournalingViewModel.onAction(
                EditJournalingIntent.GetEditJournaling(
                    id,
                    healthJournals
                )
            )
        }

        LaunchedEffect(healthJournalState.deleting) {
            healthJournalState.deleting.onSuccess {
                onEvent(EditJournalingEvent.OnGoBack)
                healthJournalViewModel.onAction(HealthJournalIntent.ResetDeletingStatus)
            }
        }

        LaunchedEffect(healthJournalState.editing) {
            healthJournalState.editing.onSuccess {
                onEvent(EditJournalingEvent.OnGoBack)
                healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                editJournalingViewModel.onAction(EditJournalingIntent.ResetState)
                healthJournalViewModel.onAction(HealthJournalIntent.ResetEditingStatus)
            }
        }

        return EditJournalingState(
            snackBarState = snackBarState,
            healthJournal = editingHealthJournalState.editingHealthJournalRecord,
            isEditing = editingHealthJournalState.isEditing,
            canSave = canSave,
            openDeleteDialog = editingHealthJournalState.openDeleteDialog,
            onEditingAction = editJournalingViewModel::onAction,
            onEvent = ::onEvent,
            onAction = healthJournalViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: EditJournalingState) = EditJournalingUI(state)
}
