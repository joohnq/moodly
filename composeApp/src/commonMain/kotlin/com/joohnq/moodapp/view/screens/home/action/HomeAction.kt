package com.joohnq.moodapp.view.screens.home.action

sealed class HomeAction {
    data object OnNavigateToFreudScore: HomeAction()
    data object OnNavigateToMood: HomeAction()
    data object OnNavigateToHealthJournal: HomeAction()
}
