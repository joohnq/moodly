package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.self_journal.impl.ui.mapper.toDomain
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.mapper.onSuccess
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

    fun onEvent(event: EditSelfJournalContract.Event) =
        when (event) {
            EditSelfJournalContract.Event.OnGoBack -> onGoBack()
            EditSelfJournalContract.Event.OnSave ->
                selfJournalViewModel.onAction(
                    SelfJournalContract.Intent.Update(
                        state.editingSelfJournalRecord
                    )
                )
        }

    LaunchedEffect(selfJournalState.records) {
        selfJournalState.records.onSuccess { selfJournals ->
            val item = selfJournals.find { it.id == id } ?: return@onSuccess
            editSelfJournalViewModel.onAction(
                EditSelfJournalContract.Intent.Set(item.toDomain())
            )
        }
    }

    LaunchedEffect(selfJournalViewModel) {
        selfJournalViewModel.sideEffect.collect { effect ->
            when (effect) {
                SelfJournalContract.SideEffect.SelfJournalDeleted -> {
                    onEvent(EditSelfJournalContract.Event.OnGoBack)
                }

                SelfJournalContract.SideEffect.Updated -> {
                    editSelfJournalViewModel.onAction(EditSelfJournalContract.Intent.ClearEditingState)
                    editSelfJournalViewModel.onAction(
                        EditSelfJournalContract.Intent.UpdateIsEditing(
                            false
                        )
                    )
                    selfJournalViewModel.onAction(SelfJournalContract.Intent.GetAll)
                }

                is SelfJournalContract.SideEffect.ShowError -> onError(effect.error)
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            editSelfJournalViewModel.onAction(EditSelfJournalContract.Intent.ResetState)
        }
    }

    return EditJournalingContent(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onAction = editSelfJournalViewModel::onAction,
        onEvent = ::onEvent,
        onSelfJournalAction = selfJournalViewModel::onAction
    )
}