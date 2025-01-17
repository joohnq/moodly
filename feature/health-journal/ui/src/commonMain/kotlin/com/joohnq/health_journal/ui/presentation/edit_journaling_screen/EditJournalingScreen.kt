package com.joohnq.health_journal.ui.presentation.edit_journaling_screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.state.EditJournalingState
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingIntent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalSideEffect
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import kotlinx.coroutines.launch

class EditJournalingScreen(val id: Int, private val onGoBack: () -> Unit) :
    CustomScreen<EditJournalingState>() {
    @Composable
    override fun Screen(): EditJournalingState {
        val scope = rememberCoroutineScope()
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val editJournalingViewModel: EditJournalingViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val healthJournalState by healthJournalViewModel.state.collectAsState()
        val editingHealthJournalState by editJournalingViewModel.state.collectAsState()

        val isDifferent by derivedStateOf {
            editingHealthJournalState.editingHealthJournalRecord.title != editingHealthJournalState.currentHealthJournalRecord.title ||
                    editingHealthJournalState.editingHealthJournalRecord.description != editingHealthJournalState.currentHealthJournalRecord.description
        }
        val canSave by derivedStateOf { isDifferent && editingHealthJournalState.editingHealthJournalRecord.title.isNotBlank() && editingHealthJournalState.editingHealthJournalRecord.description.isNotBlank() }

        fun onError(error: Throwable) =
            scope.launch { snackBarState.showSnackbar(error.message.toString()) }

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
