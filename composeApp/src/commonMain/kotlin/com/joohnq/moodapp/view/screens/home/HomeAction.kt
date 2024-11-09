package com.joohnq.moodapp.view.screens.home

import com.joohnq.moodapp.entities.StatsRecord

sealed class HomeAction {
    data object OnNavigateToFreudScore : HomeAction()
    data class OnNavigateToMood(val statsRecord: StatsRecord) : HomeAction()
    data object OnNavigateToHealthJournal : HomeAction()
    data object OnNavigateToMindfulJournal : HomeAction()
    data object OnNavigateToStressLevel : HomeAction()
    data object OnNavigateToMoodTracker : HomeAction()
    data object OnNavigateToSleepQuality : HomeAction()
}
