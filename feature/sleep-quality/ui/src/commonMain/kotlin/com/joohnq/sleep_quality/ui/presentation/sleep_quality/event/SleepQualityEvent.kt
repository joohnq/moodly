package com.joohnq.sleep_quality.ui.presentation.sleep_quality.event

import com.joohnq.core.ui.event.PanelEvent

sealed interface SleepQualityEvent {
    data object OnGoBack : SleepQualityEvent
    data object OnAddSleepQuality : SleepQualityEvent
    data object OnNextMonth : SleepQualityEvent
    data object OnPreviousMonth : SleepQualityEvent
    data object OnAdd : SleepQualityEvent
}

fun PanelEvent.toSleepQualityEvent(): SleepQualityEvent =
    when (this) {
        PanelEvent.OnGoBack -> SleepQualityEvent.OnGoBack
        PanelEvent.OnAdd -> SleepQualityEvent.OnAdd
    }