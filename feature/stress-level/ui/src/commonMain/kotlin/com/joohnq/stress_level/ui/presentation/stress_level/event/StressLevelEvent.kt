package com.joohnq.stress_level.ui.presentation.stress_level.event

sealed class StressLevelEvent {
    data object GoBack : StressLevelEvent()
    data object Add : StressLevelEvent()
}