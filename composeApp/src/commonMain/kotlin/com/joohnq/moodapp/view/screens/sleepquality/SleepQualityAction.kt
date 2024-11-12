package com.joohnq.moodapp.view.screens.sleepquality

sealed class SleepQualityAction {
    data object OnGoBack : SleepQualityAction()
    data object OnAdd : SleepQualityAction()
}