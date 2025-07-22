package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.event

sealed interface AddSleepQualityEvent {
    data object OnGoBack : AddSleepQualityEvent
    data object OnAdd : AddSleepQualityEvent
    data object OnNavigateToSleepQuality : AddSleepQualityEvent
}

