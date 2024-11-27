package com.joohnq.moodapp.ui.presentation.add_sleep_quality.event

sealed class AddSleepQualityEvent {
    data object OnGoBack : AddSleepQualityEvent()
    data object OnAdd : AddSleepQualityEvent()
}

