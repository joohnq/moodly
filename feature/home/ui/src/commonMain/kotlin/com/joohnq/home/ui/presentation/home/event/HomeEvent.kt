package com.joohnq.home.ui.presentation.home.event

import com.joohnq.home.ui.presentation.dashboard.event.DashboardEvent

sealed interface HomeEvent {
    data object OnNavigateToFreudScore : HomeEvent
    data object OnNavigateToMood : HomeEvent
    data object OnNavigateToSleepQuality : HomeEvent
    data object OnNavigateToHealthJournal : HomeEvent
    data object OnNavigateToMindfulJournal : HomeEvent
    data object OnNavigateToStressLevel : HomeEvent
    data object OnNavigateToAddSleep : HomeEvent
    data object OnNavigateToAddStress : HomeEvent
    data object OnNavigateToAllJournals : HomeEvent
    data object OnNavigateToAddStat : HomeEvent
    data object OnNavigateToAddJournaling : HomeEvent
    data class ShowError(val error: Throwable) : HomeEvent
}

fun HomeEvent.toDashboardEvent(): DashboardEvent =
    when (this) {
        HomeEvent.OnNavigateToAddJournaling -> DashboardEvent.OnNavigateToAddJournaling
        HomeEvent.OnNavigateToAddSleep -> DashboardEvent.OnNavigateToAddSleep
        HomeEvent.OnNavigateToAddStat -> DashboardEvent.OnNavigateToAddStat
        HomeEvent.OnNavigateToAddStress -> DashboardEvent.OnNavigateToAddStress
        HomeEvent.OnNavigateToAllJournals -> DashboardEvent.OnNavigateToAllJournals
        HomeEvent.OnNavigateToFreudScore -> DashboardEvent.OnNavigateToFreudScore
        HomeEvent.OnNavigateToHealthJournal -> DashboardEvent.OnNavigateToHealthJournal
        HomeEvent.OnNavigateToMindfulJournal -> DashboardEvent.OnNavigateToMindfulJournal
        HomeEvent.OnNavigateToMood -> DashboardEvent.OnNavigateToMood
        HomeEvent.OnNavigateToSleepQuality -> DashboardEvent.OnNavigateToSleepQuality
        HomeEvent.OnNavigateToStressLevel -> DashboardEvent.OnNavigateToStressLevel
        else -> throw Exception("Unable to do this operation")
    }
