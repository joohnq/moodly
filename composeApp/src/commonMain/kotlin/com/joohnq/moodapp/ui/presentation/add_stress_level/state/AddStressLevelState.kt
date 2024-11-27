package com.joohnq.moodapp.ui.presentation.add_stress_level.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddingStressLevelViewModelState
import com.joohnq.moodapp.viewmodel.StressLevelIntent

data class AddStressLevelState(
    val snackBarState: SnackbarHostState,
    val addStressLevelViewModelState: AddingStressLevelViewModelState,
    val onAddAction: (AddStressLevelIntent) -> Unit,
    val onAction: (StressLevelIntent) -> Unit,
    val onEvent: (AddStressLevelEvent) -> Unit,
)