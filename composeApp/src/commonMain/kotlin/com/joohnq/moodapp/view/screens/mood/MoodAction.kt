package com.joohnq.moodapp.view.screens.mood

import com.joohnq.moodapp.entities.StatsRecord

sealed class MoodAction {
    data object OnPrevious : MoodAction()
    data object OnNext : MoodAction()
    data object OnGoBack : MoodAction()
    data object OnAdd : MoodAction()
    data class OnSetMood(val statsRecord: StatsRecord) : MoodAction()
}
