package com.joohnq.moodapp.ui.presentation.freud_score.event

import com.joohnq.moodapp.domain.StatsRecord

sealed class FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent()
    data class OnNavigateToMoodScreen(val statsRecord: StatsRecord) : FreudScoreEvent()
    data object OnAdd : FreudScoreEvent()
}