package com.joohnq.self_journal.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.DisposableEffect
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSelfJournalScreen(
    onGoBack: () -> Unit,
    viewModel: AddSelfJournalViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                AddSelfJournalContract.SideEffect.GoBack ->
                    onGoBack()

                is AddSelfJournalContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: AddSelfJournalContract.Event) =
        when (event) {
            AddSelfJournalContract.Event.GoBack -> onGoBack()
        }

    DisposableEffect {
        viewModel.onIntent(AddSelfJournalContract.Intent.ResetState)
    }

    AddSelfJournalContent(
        snackBarState = snackBarState,
        state = state,
        onIntent = dispatch,
        onEvent = ::onEvent
    )
}