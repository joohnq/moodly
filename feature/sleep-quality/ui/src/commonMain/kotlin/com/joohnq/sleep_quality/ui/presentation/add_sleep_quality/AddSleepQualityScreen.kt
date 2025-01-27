package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.entity.Time
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.mapper.toSleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toDomain
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityIntent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualitySideEffect
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val addSleepQualityViewModel: AddSleepQualityViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val state by addSleepQualityViewModel.state.collectAsState()

    fun onError(error: Throwable) {
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }
    }

    fun onEvent(event: AddSleepQualityEvent) =
        when (event) {
            AddSleepQualityEvent.OnGoBack -> onGoBack()
            AddSleepQualityEvent.OnAdd ->
                sleepQualityViewModel.onAction(
                    SleepQualityIntent.AddSleepQualityRecord(
                        SleepQualityRecord(
                            sleepQuality = state.mood!!.toSleepQuality(),
                            sleepInfluences = state.selectedSleepInfluences.toDomain(),
                            startSleeping = Time(state.startHour, state.startMinute),
                            endSleeping = Time(state.endHour, state.endMinute),
                        )
                    )
                )
        }

    LaunchedEffect(sleepQualityViewModel) {
        scope.launch {
            sleepQualityViewModel.sideEffect.collect { event ->
                when (event) {
                    is SleepQualitySideEffect.SleepQualityAdded -> {
                        onEvent(AddSleepQualityEvent.OnGoBack)
                        sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                    }

                    is SleepQualitySideEffect.ShowError -> onError(event.error)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addSleepQualityViewModel.onAction(AddSleepQualityIntent.ResetState)
        }
    }

    AddSleepQualityUI(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onAddAction = addSleepQualityViewModel::onAction
    )
}
