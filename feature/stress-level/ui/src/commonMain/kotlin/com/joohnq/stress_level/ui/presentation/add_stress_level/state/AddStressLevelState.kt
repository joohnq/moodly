package com.joohnq.stress_level.ui.presentation.add_stress_level.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.stress_level.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.mood.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.mood.ui.presentation.add_stress_level.AddingStressLevelViewModelState
import com.joohnq.mood.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddingStressLevelViewModelState
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent

data class AddStressLevelState(
    val snackBarState: SnackbarHostState,
    val addStressLevelViewModelState: AddingStressLevelViewModelState,
    val onAddAction: (AddStressLevelIntent) -> Unit,
    val onAction: (StressLevelIntent) -> Unit,
    val onEvent: (AddStressLevelEvent) -> Unit,
)