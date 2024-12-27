package com.joohnq.mood.ui.presentation.mood.event

import com.joohnq.domain.entity.StatsRecord

sealed class MoodEvent {
    data object OnPrevious : MoodEvent()
    data object OnNext : MoodEvent()
    data object OnGoBack : MoodEvent()
    data object OnAddStatScreen : MoodEvent()
    data class OnSetMood(val statsRecord: StatsRecord) : MoodEvent()
}
