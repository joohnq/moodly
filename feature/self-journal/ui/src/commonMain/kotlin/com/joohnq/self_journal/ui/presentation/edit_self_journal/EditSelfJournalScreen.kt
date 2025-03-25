package com.joohnq.self_journal.ui.presentation.edit_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.self_journal.ui.mapper.toDomain
import com.joohnq.self_journal.ui.presentation.edit_self_journal.event.EditSelfJournalEvent
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalIntent
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalViewModel
import com.joohnq.self_journal.ui.viewmodel.SelfJournalIntent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalSideEffect
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun EditJournalingScreen(id: Int, onGoBack: () -> Unit) {
    val scope = rememberCoroutineScope()
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val editSelfJournalViewModel: EditSelfJournalViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val selfJournalState by selfJournalViewModel.state.collectAsState()
    val state by editSelfJournalViewModel.state.collectAsState()
    val isDifferent by derivedStateOf {
        state.editingSelfJournalRecord.title != state.currentSelfJournalRecord.title ||
                state.editingSelfJournalRecord.description != state.currentSelfJournalRecord.description
    }
    val canSave by derivedStateOf { isDifferent && state.editingSelfJournalRecord.title.isNotBlank() && state.editingSelfJournalRecord.description.isNotBlank() }

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: EditSelfJournalEvent) =
        when (event) {
            EditSelfJournalEvent.OnGoBack -> onGoBack()
            EditSelfJournalEvent.OnSave ->
                selfJournalViewModel.onAction(
                    SelfJournalIntent.Update(
                        state.editingSelfJournalRecord
                    )
                )
        }

    LaunchedEffect(selfJournalState.records) {
        selfJournalState.records.onSuccess { selfJournals ->
            val item = selfJournals.find { it.id == id } ?: return@onSuccess
            editSelfJournalViewModel.onAction(
                EditSelfJournalIntent.SetEditSelfJournal(item.toDomain())
            )
        }
    }

    LaunchedEffect(selfJournalViewModel) {
        selfJournalViewModel.sideEffect.collect { effect ->
            when (effect) {
                SelfJournalSideEffect.SelfJournalDeleted -> {
                    onEvent(EditSelfJournalEvent.OnGoBack)
                }

                SelfJournalSideEffect.Updated -> {
                    editSelfJournalViewModel.onAction(EditSelfJournalIntent.ClearEditingState)
                    editSelfJournalViewModel.onAction(EditSelfJournalIntent.UpdateIsEditing(false))
                    selfJournalViewModel.onAction(SelfJournalIntent.GetAll)
                }

                is SelfJournalSideEffect.ShowError -> onError(effect.error.message.toString())
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            editSelfJournalViewModel.onAction(EditSelfJournalIntent.ResetState)
        }
    }

    return EditJournalingUI(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onAction = editSelfJournalViewModel::onAction,
        onEvent = ::onEvent,
        onSelfJournalAction = selfJournalViewModel::onAction
    )
}
