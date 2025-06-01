package com.joohnq.self_journal.ui.presentation.edit_self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.self_journal.ui.resource.mapper.toDomain
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalContract
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalViewModel
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalViewModel
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

    fun onEvent(event: EditSelfJournalContract.Event) =
        when (event) {
            EditSelfJournalContract.Event.GoBack -> onGoBack()
            EditSelfJournalContract.Event.Save ->
                selfJournalViewModel.onIntent(
                    SelfJournalContract.Intent.Update(
                        state.editingSelfJournalRecord
                    )
                )
        }

    LaunchedEffect(selfJournalState.records) {
        selfJournalState.records.onSuccess { selfJournals ->
            val item = selfJournals.find { it.id == id } ?: return@onSuccess
            editSelfJournalViewModel.onIntent(
                EditSelfJournalContract.Intent.UpdateSelfJournal(item.toDomain())
            )
        }
    }

    LaunchedEffect(selfJournalViewModel) {
        selfJournalViewModel.sideEffect.collect { effect ->
            when (effect) {
                SelfJournalContract.SideEffect.SelfJournalDeleted -> {
                    onEvent(EditSelfJournalContract.Event.GoBack)
                }

                SelfJournalContract.SideEffect.SelfJournalUpdated -> {
                    editSelfJournalViewModel.onIntent(EditSelfJournalContract.Intent.ClearEditingState)
                    editSelfJournalViewModel.onIntent(
                        EditSelfJournalContract.Intent.UpdateIsEditing(
                            false
                        )
                    )
                    selfJournalViewModel.onIntent(SelfJournalContract.Intent.GetAll)
                }

                is SelfJournalContract.SideEffect.ShowError -> onError(effect.error.message.toString())
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            editSelfJournalViewModel.onIntent(EditSelfJournalContract.Intent.ResetState)
        }
    }

    return EditJournalingUI(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onEditIntent = editSelfJournalViewModel::onIntent,
        onEvent = ::onEvent,
        onIntent = selfJournalViewModel::onIntent
    )
}
