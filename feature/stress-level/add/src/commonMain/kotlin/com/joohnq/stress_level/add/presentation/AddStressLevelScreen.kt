package com.joohnq.stress_level.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddStressLevelScreen(
    onNavigateToStressStressors: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is AddStressLevelContract.SideEffect.OnGoBack ->
                    onGoBack()

                is AddStressLevelContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }

                AddStressLevelContract.SideEffect.NavigateToStressStressors ->
                    onNavigateToStressStressors()
            }
        }

    fun onEvent(event: AddStressLevelContract.Event) {
        when (event) {
            AddStressLevelContract.Event.OnGoBack -> onGoBack()
            AddStressLevelContract.Event.OnContinue -> {
                viewModel.onIntent(
                    AddStressLevelContract.Intent.Add
                )
            }
        }
    }

    AddStressLevelScreenContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}
