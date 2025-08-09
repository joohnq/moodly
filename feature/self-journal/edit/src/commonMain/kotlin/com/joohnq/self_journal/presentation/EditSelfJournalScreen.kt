package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    val (state, onIntent) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                EditSelfJournalContract.SideEffect.GoBack ->
                    onGoBack()

                is EditSelfJournalContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: EditSelfJournalContract.Event) {
        when (event) {
            EditSelfJournalContract.Event.GoBack -> onGoBack()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            EditSelfJournalContract.Intent.GetById(id)
        )
    }

    DisposableEffect {
        viewModel.onIntent(EditSelfJournalContract.Intent.ResetState)
    }

    EditJournalingContent(
        snackBarState = snackBarState,
        state = state,
        onIntent = onIntent,
        onEvent = ::onEvent
    )
}
