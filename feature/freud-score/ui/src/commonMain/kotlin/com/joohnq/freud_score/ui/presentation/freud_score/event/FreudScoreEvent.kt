package com.joohnq.freud_score.ui.presentation.freud_score.event

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared_resources.PanelEvent

sealed interface FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent
    data class NavigateToMoodScreen(val statsRecord: StatsRecord) : FreudScoreEvent
    data object OnAdd : FreudScoreEvent
}

fun PanelEvent.toFreudScoreEvent(): FreudScoreEvent =
    when (this) {
        PanelEvent.OnGoBack -> FreudScoreEvent.OnGoBack
        PanelEvent.OnAdd -> FreudScoreEvent.OnAdd
    }