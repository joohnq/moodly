package com.joohnq.stress_level.impl.ui.presentation.stressors

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelViewModel
import com.joohnq.ui.DisposableEffect
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun StressStressorsScreen(
    onGoBack: () -> Unit,
    onNavigateToStressLevelOverview: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) = viewModel.observe { sideEffect ->
        when (sideEffect) {
            is AddStressLevelContract.SideEffect.OnGoBack -> {
                onNavigateToStressLevelOverview()
            }

            is AddStressLevelContract.SideEffect.ShowError ->
                launch { snackBarState.showSnackbar(sideEffect.message) }

            else -> Unit
        }
    }

    fun onEvent(event: AddStressLevelContract.Event) =
        when (event) {
            is AddStressLevelContract.Event.OnGoBack -> onGoBack()
            else -> Unit
        }

    DisposableEffect {
        viewModel.onIntent(AddStressLevelContract.Intent.ResetState)
    }

    StressStressorsContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}