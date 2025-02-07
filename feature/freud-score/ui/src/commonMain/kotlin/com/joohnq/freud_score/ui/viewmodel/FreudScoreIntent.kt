package com.joohnq.freud_score.ui.viewmodel

import com.joohnq.mood.domain.entity.MoodRecord

sealed interface FreudScoreIntent {
    data class GetFreudScore(val records: List<MoodRecord>) : FreudScoreIntent
}