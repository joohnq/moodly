package com.joohnq.stress_level.ui.presentation.stress_stressors.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddingStressLevelViewModelState
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsEvent

data class StressStressorsState(
    val snackBarState: SnackbarHostState,
    val addStressLevelViewModelState: AddingStressLevelViewModelState,
    val onAddAction: (AddStressLevelIntent) -> Unit,
    val onEvent: (StressStressorsEvent) -> Unit,
)