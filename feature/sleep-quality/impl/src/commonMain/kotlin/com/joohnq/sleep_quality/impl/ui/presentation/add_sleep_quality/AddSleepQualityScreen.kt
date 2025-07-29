package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toDomain
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    addSleepQualityViewModel: AddSleepQualityViewModel = sharedViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by addSleepQualityViewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: AddSleepQualityContract.Event) =
        when (event) {
            AddSleepQualityContract.Event.OnGoBack -> onGoBack()
            AddSleepQualityContract.Event.OnAdd ->
                sleepQualityViewModel.onIntent(
                    SleepQualityContract.Intent.Add(
                        state.record.toDomain()
                    )
                )

            AddSleepQualityContract.Event.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
        }

    LaunchedEffect(sleepQualityViewModel) {
        sleepQualityViewModel.sideEffect.collect { event ->
            when (event) {
                is SleepQualityContract.SideEffect.ShowError -> onError(event.error)

                SleepQualityContract.SideEffect.Deleted -> {
                    onEvent(AddSleepQualityContract.Event.OnNavigateToSleepQuality)
                    sleepQualityViewModel.onIntent(SleepQualityContract.Intent.GetAll)
                }

                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addSleepQualityViewModel.onIntent(AddSleepQualityContract.Intent.ResetState)
        }
    }

    AddSleepQualityContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onAddAction = addSleepQualityViewModel::onIntent
    )
}