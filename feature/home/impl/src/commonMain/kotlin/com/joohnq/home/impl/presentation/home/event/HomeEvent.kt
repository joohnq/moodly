package com.joohnq.home.impl.presentation.home.event

sealed interface HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent
    data object OnNavigateToMood : HomeEvent
    data object OnNavigateToSleepQuality : HomeEvent
    data object OnNavigateToSelfJournal : HomeEvent
    data object OnNavigateToStressLevel : HomeEvent
    data object OnNavigateToAddSleep : HomeEvent
    data object OnNavigateToAddMood : HomeEvent
    data object OnNavigateToAddStressLevel : HomeEvent
    data object OnNavigateToSelfJournalHistory : HomeEvent
    data object OnNavigateToAddJournaling : HomeEvent
}
