package com.joohnq.moodapp.ui.presentation.add_sleep_quality.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.moodapp.viewmodel.AddSleepQualityIntent
import com.joohnq.moodapp.viewmodel.AddSleepQualityStateViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityIntent

data class AddSleepQualityState(
    val snackBarState: SnackbarHostState,
    val addSleepQualityViewModelState: AddSleepQualityStateViewModel,
    val onEvent: (AddSleepQualityEvent) -> Unit = {},
    val onAction: (SleepQualityIntent) -> Unit = {},
    val onAddAction: (AddSleepQualityIntent) -> Unit = {}
)