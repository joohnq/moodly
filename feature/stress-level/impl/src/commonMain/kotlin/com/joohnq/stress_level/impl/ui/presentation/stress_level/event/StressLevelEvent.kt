package com.joohnq.stress_level.impl.ui.presentation.stress_level.event

import com.joohnq.ui.event.PanelEvent

sealed interface StressLevelEvent {
    data object OnGoBack : StressLevelEvent
    data object onAddStressLevel : StressLevelEvent
}

fun PanelEvent.toStressLevelEvent(): StressLevelEvent =
    when (this) {
        PanelEvent.OnGoBack -> StressLevelEvent.OnGoBack
        PanelEvent.OnAdd -> StressLevelEvent.onAddStressLevel
    }