package com.joohnq.moodapp.view.screens.stresslevel

sealed class StressLevelAction {
    data object OnGoBack : StressLevelAction()
    data object OnAdd : StressLevelAction()
}