package com.joohnq.add.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun ExpressionAnalysisScreen(
    onNavigateToMood: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddMoodViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onEvent(event: AddMoodContract.Event) =
        when (event) {
            AddMoodContract.Event.OnGoBack -> onGoBack()
            else -> {}
        }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AddMoodContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                AddMoodContract.SideEffect.StatsAdded -> onNavigateToMood()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(AddMoodContract.Intent.ResetState)
        }
    }

    ExpressionAnalysisContent(
        snackBarState = snackBarState,
        description = state.record.description,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
