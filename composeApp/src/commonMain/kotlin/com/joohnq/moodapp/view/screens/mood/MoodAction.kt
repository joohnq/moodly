package com.joohnq.moodapp.view.screens.mood

sealed class MoodAction {
    data object Previous : MoodAction()
    data object Next : MoodAction()
    data object GoBack : MoodAction()
    data object OnAdd : MoodAction()
}
