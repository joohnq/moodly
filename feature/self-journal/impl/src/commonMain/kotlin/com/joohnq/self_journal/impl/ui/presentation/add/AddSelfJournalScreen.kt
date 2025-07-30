package com.joohnq.self_journal.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSelfJournalScreen(
    onGoBack: () -> Unit,
    viewModel: AddSelfJournalViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddSelfJournalContract.Event) =
        when (event) {
            AddSelfJournalContract.Event.OnGoBack -> onGoBack()
        }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                AddSelfJournalContract.SideEffect.OnGoBack -> {
                    onEvent(AddSelfJournalContract.Event.OnGoBack)
                }

                is AddSelfJournalContract.SideEffect.ShowError -> onError(effect.error)
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
