package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.DisposableEffect
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun EditSelfJournalScreen(
    id: Int,
    onGoBack: () -> Unit,
    viewModel: EditSelfJournalViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) = viewModel.observe { sideEffect ->
        when (sideEffect) {
            EditSelfJournalContract.SideEffect.OnGoBack ->
                onGoBack()

            EditSelfJournalContract.SideEffect.Updated -> {
                viewModel.onIntent(EditSelfJournalContract.Intent.ClearEditingState)

                viewModel.onIntent(
                    EditSelfJournalContract.Intent.UpdateIsEditing(
                        false
                    )
                )
            }

            is EditSelfJournalContract.SideEffect.ShowError ->
                launch { snackBarState.showSnackbar(sideEffect.message) }
        }
    }
    val isDifferent by derivedStateOf {
        state.editingSelfJournalRecord.title != state.currentSelfJournalRecord.title ||
                state.editingSelfJournalRecord.description != state.currentSelfJournalRecord.description
    }
    val canSave by derivedStateOf {
        isDifferent && state.editingSelfJournalRecord.title.isNotBlank() &&
                state.editingSelfJournalRecord.description.isNotBlank()
    }

    fun onEvent(event: EditSelfJournalContract.Event) =
        when (event) {
            EditSelfJournalContract.Event.OnGoBack -> onGoBack()
        }

    DisposableEffect {
        viewModel.onIntent(EditSelfJournalContract.Intent.ResetState)
    }

    EditJournalingContent(
        snackBarState = snackBarState,
        state = state,
        canSave = canSave,
        onIntent = dispatch,
        onEvent = ::onEvent
    )
}