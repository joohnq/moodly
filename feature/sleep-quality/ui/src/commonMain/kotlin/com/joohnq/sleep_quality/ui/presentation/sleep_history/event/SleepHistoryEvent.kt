package com.joohnq.sleep_quality.ui.presentation.sleep_history.event

sealed interface SleepHistoryEvent {
    data object OnNavigateToSleepQuality : SleepHistoryEvent
    data object OnGoBack : SleepHistoryEvent
    data object OnAddSleepQuality : SleepHistoryEvent
}

