package com.joohnq.sleep_quality.ui.presentation.sleep_history.event

import com.joohnq.shared_resources.PanelEvent

sealed interface SleepHistoryEvent {
    data class OnNavigateToSleepQuality(val id: Int) : SleepHistoryEvent
    data object OnGoBack : SleepHistoryEvent
    data object OnPreviousMonth : SleepHistoryEvent
    data object OnNextMonth : SleepHistoryEvent
    data object OnAddSleepQuality : SleepHistoryEvent
}

fun PanelEvent.toSleepHistoryEvent(): SleepHistoryEvent =
    when (this) {
        PanelEvent.OnGoBack -> SleepHistoryEvent.OnGoBack
        PanelEvent.OnAdd -> SleepHistoryEvent.OnAddSleepQuality
    }