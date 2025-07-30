package com.joohnq.sleep_quality.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewViewModel
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    sleepQualityOverviewViewModel: SleepQualityOverviewViewModel = sharedViewModel(),
    viewModel: AddSleepQualityViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by viewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: AddSleepQualityContract.Event) =
        when (event) {
            AddSleepQualityContract.Event.OnGoBack -> onGoBack()

            AddSleepQualityContract.Event.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
        }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { event ->
            when (event) {
                is AddSleepQualityContract.SideEffect.ShowError -> onError(event.error)

                AddSleepQualityContract.SideEffect.OnNavigateToNext -> {
                    onEvent(AddSleepQualityContract.Event.OnNavigateToSleepQuality)
                }
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
