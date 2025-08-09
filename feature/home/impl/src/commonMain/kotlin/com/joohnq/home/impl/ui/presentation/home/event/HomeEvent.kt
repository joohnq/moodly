package com.joohnq.home.impl.ui.presentation.home.event

sealed interface HomeEvent {
    data object NavigateToFreudScore : HomeEvent

    data object NavigateToMoodOverview : HomeEvent

    data object NavigateToSleepQualityOverview : HomeEvent

    data object NavigateToSelfJournalOverview : HomeEvent

    data object NavigateToStressLevelOverview : HomeEvent

    data object NavigateToGratefulnessOverview : HomeEvent

    data object NavigateToAddSleepQuality : HomeEvent

    data object NavigateToAddMood : HomeEvent

    data object NavigateToAddStressLevel : HomeEvent

    data object NavigateToAddSelfJournal : HomeEvent

    data object OnNavigateToAddGratefulness : HomeEvent
}
