package com.joohnq.stress_level.ui.presentation.stress_level.event

sealed interface StressLevelEvent {
    data object GoBack : StressLevelEvent
    data object Add : StressLevelEvent
}