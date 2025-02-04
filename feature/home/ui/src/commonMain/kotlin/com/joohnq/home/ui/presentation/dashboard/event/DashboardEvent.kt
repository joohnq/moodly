package com.joohnq.home.ui.presentation.dashboard.event

import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.navigation.Destination

sealed interface DashboardEvent {
    data object OnNavigateToAddJournaling : DashboardEvent
    data object OnNavigateToAddStress : DashboardEvent
    data object OnNavigateToAddStat : DashboardEvent
    data object OnNavigateToAllJournals : DashboardEvent
    data object OnNavigateToFreudScore : DashboardEvent
    data object OnNavigateToMood : DashboardEvent
    data object OnNavigateToHealthJournal : DashboardEvent
    data object OnNavigateToMindfulJournal : DashboardEvent
    data object OnNavigateToSleepQuality : DashboardEvent
    data object OnNavigateToStressLevel : DashboardEvent
    data class OnNavigateToEditJournaling(val id: Int) : DashboardEvent
    data object OnNavigateToSelfJournalHistory : DashboardEvent
    data object OnNavigateToAddSleep : DashboardEvent
    data class OnNavigateTo(val destination: Destination) : DashboardEvent
}

fun JournalingEvent.toDashboardEvent(): DashboardEvent =
    when (this) {
        JournalingEvent.OnNavigateToAllJournals -> DashboardEvent.OnNavigateToAllJournals
        is JournalingEvent.OnNavigateToEditJournaling -> DashboardEvent.OnNavigateToEditJournaling(this.id)
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
    }
