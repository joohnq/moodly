package com.joohnq.sleep_quality.ui.presentation.sleep_quality.event

sealed interface SleepQualityEvent {
    data object GoBack : SleepQualityEvent
    data object OnNavigateSleepHistory : SleepQualityEvent
    data object Add : SleepQualityEvent
}