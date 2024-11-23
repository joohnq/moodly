package com.joohnq.moodapp.ui.presentation.home.event

sealed class HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent()
    data object OnNavigateToMood : HomeEvent()
    data object OnNavigateToHealthJournal : HomeEvent()
    data object OnNavigateToMindfulJournal : HomeEvent()
    data object OnNavigateToStressLevel : HomeEvent()
    data object OnNavigateToSleepQuality : HomeEvent()
}
