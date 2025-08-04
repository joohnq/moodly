package com.joohnq.sleep_quality.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    viewModel: AddSleepQualityViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onEvent(event: AddSleepQualityContract.Event) =
        when (event) {
            AddSleepQualityContract.Event.OnGoBack -> onGoBack()
        }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AddSleepQualityContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                AddSleepQualityContract.SideEffect.OnNavigateToNext -> onNavigateToSleepQuality()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(AddSleepQualityContract.Intent.ResetState)
        }
    }

    AddSleepQualityContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
