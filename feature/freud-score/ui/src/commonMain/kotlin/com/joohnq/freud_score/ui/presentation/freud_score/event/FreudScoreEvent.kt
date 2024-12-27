package com.joohnq.freud_score.ui.presentation.freud_score.event

import com.joohnq.domain.entity.StatsRecord

sealed class FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent()
    data class OnNavigateToMoodScreen(val statsRecord: StatsRecord) : FreudScoreEvent()
    data object OnAdd : FreudScoreEvent()
}