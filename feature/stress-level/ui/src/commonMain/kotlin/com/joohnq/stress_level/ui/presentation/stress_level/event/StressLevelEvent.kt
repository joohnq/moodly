package com.joohnq.stress_level.ui.presentation.stress_level.event

import com.joohnq.core.ui.event.PanelEvent

sealed interface StressLevelEvent {
    data object OnGoBack : StressLevelEvent
    data object onAddStressLevel : StressLevelEvent
}

fun PanelEvent.toStressLevelEvent(): StressLevelEvent =
    when (this) {
        PanelEvent.OnGoBack -> StressLevelEvent.OnGoBack
        PanelEvent.OnAdd -> StressLevelEvent.onAddStressLevel
    }