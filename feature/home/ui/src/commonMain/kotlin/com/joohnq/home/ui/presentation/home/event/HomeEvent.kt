package com.joohnq.home.ui.presentation.home.event

sealed interface HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent
    data object OnNavigateToMood : HomeEvent
    data object OnNavigateToSleepQuality : HomeEvent
    data object OnNavigateToSelfJournal : HomeEvent
    data object OnNavigateToMindfulJournal : HomeEvent
    data object OnNavigateToStressLevel : HomeEvent
    data object OnNavigateToAddSleep : HomeEvent
    data object OnNavigateToAddStress : HomeEvent
    data object OnNavigateToAllJournals : HomeEvent
    data object OnNavigateToAddStat : HomeEvent
    data object OnNavigateToAddJournaling : HomeEvent
}