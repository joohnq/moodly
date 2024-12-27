package com.joohnq.sleep_quality.ui.presentation.sleep_quality.event

sealed class SleepQualityEvent {
    data object OnGoBack : SleepQualityEvent()
    data object OnAdd : SleepQualityEvent()
}