package com.joohnq.stress_level.ui.presentation.stress_level.event

import com.joohnq.shared_resources.PanelEvent

sealed interface StressLevelEvent {
    data object OnGoBack : StressLevelEvent
    data object OnAdd : StressLevelEvent
}

fun PanelEvent.toStressLevelEvent(): StressLevelEvent =
    when (this) {
        PanelEvent.OnGoBack -> StressLevelEvent.OnGoBack
        PanelEvent.OnAdd -> StressLevelEvent.OnAdd
    }