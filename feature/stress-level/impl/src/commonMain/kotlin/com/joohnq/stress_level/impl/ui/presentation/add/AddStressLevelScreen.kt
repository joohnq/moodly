package com.joohnq.stress_level.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun AddStressLevelScreen(
    onNavigateToStressStressors: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

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

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AddStressLevelContract.SideEffect.OnGoBack ->
                    onGoBack()

                is AddStressLevelContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                AddStressLevelContract.SideEffect.NavigateToStressStressors ->
                    onNavigateToStressStressors()
            }
        }
    }

    AddStressLevelScreenContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
