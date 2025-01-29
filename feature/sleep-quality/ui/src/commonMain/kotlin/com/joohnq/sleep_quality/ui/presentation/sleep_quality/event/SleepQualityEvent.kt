package com.joohnq.sleep_quality.ui.presentation.sleep_quality.event

import com.joohnq.shared_resources.PanelEvent

sealed interface SleepQualityEvent {
    data object OnGoBack : SleepQualityEvent
    data object OnAdd : SleepQualityEvent
}

fun PanelEvent.toSleepQualityEvent(): SleepQualityEvent =
    when (this) {
        PanelEvent.OnGoBack -> SleepQualityEvent.OnGoBack
        PanelEvent.OnAdd -> SleepQualityEvent.OnAdd
    }