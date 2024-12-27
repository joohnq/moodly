package com.joohnq.stress_level.ui.presentation.stress_level.event

sealed class StressLevelEvent {
    data object OnGoBack : StressLevelEvent()
    data object OnAdd : StressLevelEvent()
}