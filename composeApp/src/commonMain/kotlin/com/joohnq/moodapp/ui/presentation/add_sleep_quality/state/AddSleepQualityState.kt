package com.joohnq.moodapp.ui.presentation.add_sleep_quality.state

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TimePickerState
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.moodapp.viewmodel.SleepQualityIntent

data class AddSleepQualityState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val snackBarState: SnackbarHostState,
    val showStartTimePickerDialog: Boolean,
    val showEndTimePickerDialog: Boolean,
    val startTimePickerState: TimePickerState,
    val endTimePickerState: TimePickerState,
    val selectedMood: Mood?,
    val selectedSleepInfluences: List<SleepInfluences>,
    val onEvent2: (AddSleepQualityEvent) -> Unit = {},
    val onAction: (SleepQualityIntent) -> Unit = {}
)