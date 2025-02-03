package com.joohnq.home.ui.presentation.dashboard.event

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
}