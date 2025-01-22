package com.joohnq.freud_score.ui.presentation.freud_score.event

import com.joohnq.mood.domain.entity.StatsRecord

sealed interface FreudScoreEvent {
    data object GoBack : FreudScoreEvent
    data class NavigateToMoodScreen(val statsRecord: StatsRecord) : FreudScoreEvent
    data object Add : FreudScoreEvent
}