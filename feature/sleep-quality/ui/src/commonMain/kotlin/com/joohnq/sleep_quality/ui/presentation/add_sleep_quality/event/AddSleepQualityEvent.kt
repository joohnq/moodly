package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.event

sealed interface AddSleepQualityEvent {
    data object OnGoBack : AddSleepQualityEvent
    data object OnAdd : AddSleepQualityEvent
}

