package com.joohnq.moodapp.view.screens.home

sealed class HomeAction {
    data object OnNavigateToFreudScore : HomeAction()
    data object OnNavigateToMood : HomeAction()
    data object OnNavigateToHealthJournal : HomeAction()
    data object OnNavigateToMindfulJournal : HomeAction()
    data object OnNavigateToStressLevel : HomeAction()
    data object OnNavigateToMoodTracker : HomeAction()
    data object OnNavigateToSleepQuality : HomeAction()
}
