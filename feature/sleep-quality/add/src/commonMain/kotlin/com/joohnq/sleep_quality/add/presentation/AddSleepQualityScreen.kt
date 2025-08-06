package com.joohnq.sleep_quality.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.DisposableEffect
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    viewModel: AddSleepQualityViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is AddSleepQualityContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }

                AddSleepQualityContract.SideEffect.NavigateToNext -> onNavigateToSleepQuality()
            }
        }

    fun onEvent(event: AddSleepQualityContract.Event) {
        when (event) {
            AddSleepQualityContract.Event.GoBack -> onGoBack()
        }
    }

    DisposableEffect {
        viewModel.onIntent(AddSleepQualityContract.Intent.ResetState)
    }

    AddSleepQualityContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}
