package com.joohnq.sleep_quality.ui.presentation.sleep_quality.event

sealed interface SleepQualityEvent {
    data object OnGoBack : SleepQualityEvent
    data object OnNavigateToAddSleepQuality : SleepQualityEvent
    data object OnNavigateToSleepHistory : SleepQualityEvent
}
