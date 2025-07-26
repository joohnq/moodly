package com.joohnq.home.impl

import com.joohnq.home.impl.presentation.home.event.HomeEvent
import com.joohnq.home.impl.presentation.viewmodel.DashboardContract

fun HomeEvent.toDashboardEvent(): DashboardContract.Event =
    when (this) {
        HomeEvent.OnNavigateToAddJournaling -> DashboardContract.Event.OnNavigateToAddJournaling
        HomeEvent.OnNavigateToAddSleep -> DashboardContract.Event.OnNavigateToAddSleep
        HomeEvent.OnNavigateToAddMood -> DashboardContract.Event.OnNavigateToAddMood
        HomeEvent.OnNavigateToAddStressLevel -> DashboardContract.Event.OnNavigateToAddStressLevel
        HomeEvent.OnNavigateToSelfJournalHistory -> DashboardContract.Event.OnNavigateToSelfJournalHistory
        HomeEvent.OnNavigateToFreudScore -> DashboardContract.Event.OnNavigateToFreudScore
        HomeEvent.OnNavigateToSelfJournal -> DashboardContract.Event.OnNavigateToSelfJournal
        HomeEvent.OnNavigateToMood -> DashboardContract.Event.OnNavigateToMood
        HomeEvent.OnNavigateToSleepQuality -> DashboardContract.Event.OnNavigateToSleepQuality
        HomeEvent.OnNavigateToStressLevel -> DashboardContract.Event.OnNavigateToStressLevel
    }
