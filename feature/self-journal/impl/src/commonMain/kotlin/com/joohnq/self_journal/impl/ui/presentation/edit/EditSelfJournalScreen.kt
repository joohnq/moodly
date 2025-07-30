package com.joohnq.self_journal.impl.ui.presentation.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun EditJournalingScreen(
    id: Int,
    onGoBack: () -> Unit,
    viewModel: EditSelfJournalViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()
    val isDifferent by derivedStateOf {
        state.editingSelfJournalRecord.title != state.currentSelfJournalRecord.title ||
            state.editingSelfJournalRecord.description != state.currentSelfJournalRecord.description
    }
    val canSave by derivedStateOf {
        isDifferent && state.editingSelfJournalRecord.title.isNotBlank() &&
            state.editingSelfJournalRecord.description.isNotBlank()
    }

    fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: EditSelfJournalContract.Event) =
        when (event) {
            EditSelfJournalContract.Event.OnGoBack -> onGoBack()
        }

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            EditSelfJournalContract.Intent.GetById(id)
        )
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                EditSelfJournalContract.SideEffect.OnGoBack -> {
                    onEvent(EditSelfJournalContract.Event.OnGoBack)
                }

                EditSelfJournalContract.SideEffect.Updated -> {
                    viewModel.onIntent(EditSelfJournalContract.Intent.ClearEditingState)
                    viewModel.onIntent(
                        EditSelfJournalContract.Intent.UpdateIsEditing(
                            false
                        )
                    )
                }

                is EditSelfJournalContract.SideEffect.ShowError -> onError(effect.error)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(EditSelfJournalContract.Intent.ResetState)
        }
    }

    return EditJournalingContent(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
