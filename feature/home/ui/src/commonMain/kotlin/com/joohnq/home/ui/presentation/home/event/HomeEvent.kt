package com.joohnq.home.ui.presentation.home.event

sealed interface HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent
    data object OnNavigateToMood : HomeEvent
    data object OnNavigateToHealthJournal : HomeEvent
    data object OnNavigateToMindfulJournal : HomeEvent
    data object OnNavigateToStressLevel : HomeEvent
    data object OnNavigateToSleepHistory : HomeEvent
}
