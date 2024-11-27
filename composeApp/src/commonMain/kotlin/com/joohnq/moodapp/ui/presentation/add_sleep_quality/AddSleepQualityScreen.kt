package com.joohnq.moodapp.ui.presentation.add_sleep_quality

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.state.AddSleepQualityState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.AddSleepQualityIntent
import com.joohnq.moodapp.viewmodel.AddSleepQualityViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.launch

class AddSleepQualityScreen : CustomScreen<AddSleepQualityState>() {
    @Composable
    override fun Screen(): AddSleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val addSleepQualityViewModel: AddSleepQualityViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val addSleepQualityState by addSleepQualityViewModel.addSleepQualityStateViewModel.collectAsState()

        fun onEvent(event: AddSleepQualityEvent) =
            when (event) {
                AddSleepQualityEvent.OnGoBack -> onGoBack()
                AddSleepQualityEvent.OnAdd ->
                    sleepQualityViewModel.onAction(
                        SleepQualityIntent.AddSleepQualityRecord(
                            SleepQualityRecord.Builder()
                                .setSleepQualityByMood(addSleepQualityState.mood!!)
                                .setEndSleeping(
                                    addSleepQualityState.endHour,
                                    addSleepQualityState.endMinute
                                )
                                .setStartSleeping(
                                    addSleepQualityState.startHour,
                                    addSleepQualityState.startMinute
                                )
                                .setSleepInfluences(
                                    addSleepQualityState.selectedSleepInfluences
                                )
                                .build()
                        )
                    )
            }

        LaunchedEffect(sleepQualityState.adding) {
            sleepQualityState.adding.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onEvent(AddSleepQualityEvent.OnGoBack)
                    sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                    sleepQualityViewModel.onAction(SleepQualityIntent.ResetAddingStatus)
                },
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                addSleepQualityViewModel.onAction(AddSleepQualityIntent.ResetState)
            }
        }

        return AddSleepQualityState(
            snackBarState = snackBarState,
            addSleepQualityViewModelState = addSleepQualityState,
            onAction = sleepQualityViewModel::onAction,
            onEvent = ::onEvent,
            onAddAction = addSleepQualityViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: AddSleepQualityState) = AddSleepQualityUI(state)
}
