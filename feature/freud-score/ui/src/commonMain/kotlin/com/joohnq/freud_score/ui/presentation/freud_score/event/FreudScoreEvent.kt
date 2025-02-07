package com.joohnq.freud_score.ui.presentation.freud_score.event

import com.joohnq.mood.domain.entity.MoodRecord

sealed interface FreudScoreEvent {
    data object OnGoBack : FreudScoreEvent
    data class NavigateToMoodScreen(val record: MoodRecord) : FreudScoreEvent
    data object OnAdd : FreudScoreEvent
}
