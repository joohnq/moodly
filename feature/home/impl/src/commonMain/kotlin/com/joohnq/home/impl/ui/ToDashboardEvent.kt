package com.joohnq.home.impl.ui

import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract
import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent

fun HomeEvent.toDashboardEvent(): DashboardContract.Event =
    when (this) {
        HomeEvent.NavigateToAddSelfJournal -> DashboardContract.Event.NavigateToAddSelfJournal
        HomeEvent.NavigateToAddSleepQuality -> DashboardContract.Event.NavigateToAddSleepQuality
        HomeEvent.NavigateToAddMood -> DashboardContract.Event.NavigateToAddMood
        HomeEvent.NavigateToAddStressLevel -> DashboardContract.Event.NavigateToAddStressLevel
        HomeEvent.NavigateToFreudScore -> DashboardContract.Event.NavigateToFreudScore
        HomeEvent.NavigateToSelfJournalOverview -> DashboardContract.Event.NavigateToSelfJournalOverview
        HomeEvent.NavigateToMoodOverview -> DashboardContract.Event.NavigateToMoodOverview
        HomeEvent.NavigateToSleepQualityOverview -> DashboardContract.Event.NavigateToSleepQualityOverview
        HomeEvent.NavigateToStressLevelOverview -> DashboardContract.Event.NavigateToStressLevelOverview
        HomeEvent.OnNavigateToAddGratefulness -> DashboardContract.Event.NavigateToAddGratefulness
        HomeEvent.NavigateToGratefulnessOverview -> DashboardContract.Event.NavigateToGratefulnessOverview
    }
