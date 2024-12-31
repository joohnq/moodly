package com.joohnq.stress_level.ui.presentation.add_stress_level.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.stress_level.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddingStressLevelViewModelState
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent

data class AddStressLevelState(
    val snackBarState: SnackbarHostState,
    val state: AddingStressLevelViewModelState,
    val onAddAction: (AddStressLevelIntent) -> Unit,
    val onAction: (StressLevelIntent) -> Unit,
    val onEvent: (AddStressLevelEvent) -> Unit,
)