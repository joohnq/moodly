package com.joohnq.moodapp.ui.presentation.mood.event

import com.joohnq.moodapp.domain.StatsRecord

sealed class MoodEvent {
    data object OnPrevious : MoodEvent()
    data object OnNext : MoodEvent()
    data object OnGoBack : MoodEvent()
    data object OnAddStatScreen : MoodEvent()
    data class OnSetMood(val statsRecord: StatsRecord) : MoodEvent()
}
