package com.joohnq.home.ui.presentation.dashboard.event

import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.navigation.Destination

sealed interface DashboardEvent {
    data object OnNavigateToAddJournaling : DashboardEvent
    data object OnNavigateToAddStress : DashboardEvent
    data object OnNavigateToAddMood : DashboardEvent
    data object OnNavigateToFreudScore : DashboardEvent
    data object OnNavigateToMood : DashboardEvent
    data object OnNavigateToSelfJournal : DashboardEvent
    data object OnNavigateToSleepQuality : DashboardEvent
    data object OnNavigateToStressLevel : DashboardEvent
    data object OnNavigateToAddStressLevel : DashboardEvent
    data class OnNavigateToEditJournaling(val id: Int) : DashboardEvent
    data object OnNavigateToSelfJournalHistory : DashboardEvent
    data object OnNavigateToAddSleep : DashboardEvent
    data class OnNavigateTo(val destination: Destination) : DashboardEvent
}

fun HomeEvent.toDashboardEvent(): DashboardEvent =
    when (this) {
        HomeEvent.OnNavigateToAddJournaling -> DashboardEvent.OnNavigateToAddJournaling
        HomeEvent.OnNavigateToAddSleep -> DashboardEvent.OnNavigateToAddSleep
        HomeEvent.OnNavigateToAddMood -> DashboardEvent.OnNavigateToAddMood
        HomeEvent.OnNavigateToAddStressLevel -> DashboardEvent.OnNavigateToAddStressLevel
        HomeEvent.OnNavigateToSelfJournalHistory -> DashboardEvent.OnNavigateToSelfJournalHistory
        HomeEvent.OnNavigateToFreudScore -> DashboardEvent.OnNavigateToFreudScore
        HomeEvent.OnNavigateToSelfJournal -> DashboardEvent.OnNavigateToSelfJournal
        HomeEvent.OnNavigateToMood -> DashboardEvent.OnNavigateToMood
        HomeEvent.OnNavigateToSleepQuality -> DashboardEvent.OnNavigateToSleepQuality
        HomeEvent.OnNavigateToStressLevel -> DashboardEvent.OnNavigateToStressLevel
    }
