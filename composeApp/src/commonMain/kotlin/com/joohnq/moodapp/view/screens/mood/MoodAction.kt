package com.joohnq.moodapp.view.screens.mood

import com.joohnq.moodapp.entities.StatsRecord

sealed class MoodAction {
    data object Previous : MoodAction()
    data object Next : MoodAction()
    data object GoBack : MoodAction()
    data object OnAdd : MoodAction()
    data class OnSetMood(val statsRecord: StatsRecord) : MoodAction()
}
