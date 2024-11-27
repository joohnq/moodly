package com.joohnq.moodapp.ui.presentation.stress_stressors.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddingStressLevelViewModelState

data class StressStressorsState(
    val snackBarState: SnackbarHostState,
    val addStressLevelViewModelState: AddingStressLevelViewModelState,
    val onAddAction: (AddStressLevelIntent) -> Unit,
    val onEvent: (StressStressorsEvent) -> Unit,
)