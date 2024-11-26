package com.joohnq.moodapp.ui.presentation.add_sleep_quality

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.state.AddSleepQualityState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.launch

class AddSleepQualityScreen : CustomScreen<AddSleepQualityState>() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Screen(): AddSleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val startTimePickerState = rememberTimePickerState(
            initialHour = sleepQualityState.adding.startHour,
            initialMinute = sleepQualityState.adding.startMinute,
            is24Hour = true,
        )
        val endTimePickerState = rememberTimePickerState(
            initialHour = sleepQualityState.adding.endHour,
            initialMinute = sleepQualityState.adding.endMinute,
            is24Hour = true,
        )

        fun onEvent(event: AddSleepQualityEvent) =
            when (event) {
                AddSleepQualityEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(sleepQualityState.adding.status) {
            sleepQualityState.adding.status.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                    onEvent(AddSleepQualityEvent.OnGoBack)
                    sleepQualityViewModel.onAction(SleepQualityIntent.ResetAdding)
                },
            )
        }

        return AddSleepQualityState(
            snackBarState = snackBarState,
            showStartTimePickerDialog = sleepQualityState.adding.showStartTimePickerDialog,
            showEndTimePickerDialog = sleepQualityState.adding.showEndTimePickerDialog,
            startTimePickerState = startTimePickerState,
            endTimePickerState = endTimePickerState,
            selectedMood = sleepQualityState.adding.mood,
            selectedSleepInfluences = sleepQualityState.adding.selectedSleepInfluences,
            onAction = sleepQualityViewModel::onAction,
            onEvent2 = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AddSleepQualityState) = AddSleepQualityUI(state)
}
