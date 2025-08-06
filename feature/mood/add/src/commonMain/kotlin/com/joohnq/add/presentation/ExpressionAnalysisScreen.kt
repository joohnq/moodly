package com.joohnq.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.DisposableEffect
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun ExpressionAnalysisScreen(
    onNavigateToMood: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddMoodViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is AddMoodContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }

                AddMoodContract.SideEffect.NavigateNext -> onNavigateToMood()
            }
        }

    fun onEvent(event: AddMoodContract.Event) =
        when (event) {
            AddMoodContract.Event.GoBack -> onGoBack()
            else -> {}
        }

    DisposableEffect {
        viewModel.onIntent(AddMoodContract.Intent.ResetState)
    }

    ExpressionAnalysisContent(
        snackBarState = snackBarState,
        description = state.record.description,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}
