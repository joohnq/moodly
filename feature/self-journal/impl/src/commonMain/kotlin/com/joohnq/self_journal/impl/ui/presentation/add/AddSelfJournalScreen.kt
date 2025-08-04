package com.joohnq.self_journal.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun AddSelfJournalScreen(
    onGoBack: () -> Unit,
    viewModel: AddSelfJournalViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onEvent(event: AddSelfJournalContract.Event) =
        when (event) {
            AddSelfJournalContract.Event.OnGoBack -> onGoBack()
        }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                AddSelfJournalContract.SideEffect.OnGoBack -> {
                    onEvent(AddSelfJournalContract.Event.OnGoBack)
                }

                is AddSelfJournalContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(AddSelfJournalContract.Intent.ResetState)
        }
    }

    return AddSelfJournalContent(
        snackBarState = snackBarState,
        state = state,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
