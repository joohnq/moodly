package com.joohnq.moodapp.ui.presentation.sleep_quality.event

sealed class SleepQualityEvent {
    data object OnGoBack : SleepQualityEvent()
    data object OnAdd : SleepQualityEvent()
}