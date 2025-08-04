package com.joohnq.stress_level.impl.ui.presentation.stressors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressStressorsScreen(
    onGoBack: () -> Unit,
    onNavigateToStressLevelOverview: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onEvent(event: AddStressLevelContract.Event) =
        when (event) {
            is AddStressLevelContract.Event.OnGoBack -> onGoBack()
            else -> Unit
        }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AddStressLevelContract.SideEffect.OnGoBack -> {
                    onNavigateToStressLevelOverview()
                }

                is AddStressLevelContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(AddStressLevelContract.Intent.ResetState)
        }
    }

    StressStressorsContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
