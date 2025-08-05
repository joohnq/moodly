package com.joohnq.home.impl.ui

import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract

fun HomeEvent.toDashboardEvent(): DashboardContract.Event =
    when (this) {
        HomeEvent.OnNavigateToAddJournaling -> DashboardContract.Event.NavigateToAddSelfJournal
        HomeEvent.OnNavigateToAddSleep -> DashboardContract.Event.NavigateToAddSleepQuality
        HomeEvent.OnNavigateToAddMood -> DashboardContract.Event.NavigateToAddMood
        HomeEvent.OnNavigateToAddStressLevel -> DashboardContract.Event.OnNavigateToAddStressLevel
        HomeEvent.OnNavigateToSelfJournalHistory -> DashboardContract.Event.NavigateToSelfJournalHistory
        HomeEvent.OnNavigateToFreudScore -> DashboardContract.Event.NavigateToFreudScore
        HomeEvent.OnNavigateToSelfJournal -> DashboardContract.Event.NavigateToSelfJournal
        HomeEvent.OnNavigateToMood -> DashboardContract.Event.NavigateToMoodOverview
        HomeEvent.OnNavigateToSleepQuality -> DashboardContract.Event.NavigateToSleepQuality
        HomeEvent.OnNavigateToStressLevel -> DashboardContract.Event.NavigateToStressLevelOverview
    }