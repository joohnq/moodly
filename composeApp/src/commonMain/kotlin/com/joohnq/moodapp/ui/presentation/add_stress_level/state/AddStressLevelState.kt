package com.joohnq.moodapp.ui.presentation.add_stress_level.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.moodapp.viewmodel.StressLevelIntent

data class AddStressLevelState(
    val snackBarState: SnackbarHostState,
    val selectedStressLevel: StressLevel,
    val sliderValue: Float,
    val onAction: (StressLevelIntent) -> Unit,
    val onNavigation: (AddStressLevelEvent) -> Unit,
)