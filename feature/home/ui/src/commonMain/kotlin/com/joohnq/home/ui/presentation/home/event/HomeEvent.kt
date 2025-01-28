package com.joohnq.home.ui.presentation.home.event

sealed interface HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent
    data object OnNavigateToMood : HomeEvent
    data object OnNavigateToHealthJournal : HomeEvent
    data object OnNavigateToMindfulJournal : HomeEvent
    data class OnNavigateToStressLevel(val id: Int) : HomeEvent
    data object OnNavigateToSleepHistory : HomeEvent
    data object OnNavigateToAddSleep : HomeEvent
    data object OnNavigateToStressHistory : HomeEvent
    data object OnNavigateToAddStress : HomeEvent
    data object OnNavigateToAllJournals : HomeEvent
    data object OnNavigateToAddJournaling : HomeEvent
    data class ShowError(val error: Throwable) : HomeEvent
}
