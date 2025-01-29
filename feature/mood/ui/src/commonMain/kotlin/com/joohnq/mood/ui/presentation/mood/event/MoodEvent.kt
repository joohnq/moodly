package com.joohnq.mood.ui.presentation.mood.event

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared_resources.PanelEvent

sealed interface MoodEvent {
    data object OnPrevious : MoodEvent
    data object OnNext : MoodEvent
    data object OnGoBack : MoodEvent
    data object OnAddStatScreen : MoodEvent
    data class OnSetMood(val statsRecord: StatsRecord) : MoodEvent
}

fun PanelEvent.toMoodEvent(): MoodEvent =
    when (this) {
        PanelEvent.OnGoBack -> MoodEvent.OnGoBack
        PanelEvent.OnAdd -> MoodEvent.OnAddStatScreen
    }
