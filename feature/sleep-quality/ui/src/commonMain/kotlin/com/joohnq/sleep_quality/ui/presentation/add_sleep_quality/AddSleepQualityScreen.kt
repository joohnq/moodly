package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality

import androidx.compose.runtime.*
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.a_sleep_quality_record_has_already_been_added_for_today
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.domain.exception.SleepQualityException
import com.joohnq.sleep_quality.ui.mapper.toDomain
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityIntent
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualitySideEffect
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddSleepQualityScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val addSleepQualityViewModel: AddSleepQualityViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val state by addSleepQualityViewModel.state.collectAsState()
    val alreadyBeenAddedToday = stringResource(Res.string.a_sleep_quality_record_has_already_been_added_for_today)

    fun onError(error: Throwable) {
        scope.launch {
            val error = when (error) {
                is SleepQualityException.AlreadyBeenAddedToday -> alreadyBeenAddedToday
                else -> error.message.toString()
            }
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: AddSleepQualityEvent) =
        when (event) {
            AddSleepQualityEvent.OnGoBack -> onGoBack()
            AddSleepQualityEvent.OnAdd ->
                sleepQualityViewModel.onAction(
                    SleepQualityIntent.AddSleepQualityRecord(
                        state.record.toDomain()
                    )
                )

            AddSleepQualityEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
        }

    LaunchedEffect(sleepQualityViewModel) {
        sleepQualityViewModel.sideEffect.collect { event ->
            when (event) {
                is SleepQualitySideEffect.SleepQualityAdded -> {
                    onEvent(AddSleepQualityEvent.OnNavigateToSleepQuality)
                    sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                }

                is SleepQualitySideEffect.ShowError -> onError(event.error)
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
