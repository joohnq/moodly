package com.joohnq.moodapp.ui.presentation.stress_stressors.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.moodapp.viewmodel.StressLevelIntent

data class StressStressorsState(
    val snackBarState: SnackbarHostState,
    val selectedStressors: List<Stressor>,
    val otherValueError: String?,
    val otherValue: String,
    val onAction: (StressLevelIntent) -> Unit = {},
    val onEvent: (StressStressorsEvent) -> Unit = {},
)