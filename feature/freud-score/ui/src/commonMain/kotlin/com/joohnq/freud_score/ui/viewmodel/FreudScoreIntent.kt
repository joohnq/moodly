package com.joohnq.freud_score.ui.viewmodel

import com.joohnq.mood.ui.resource.MoodRecordResource

sealed interface FreudScoreIntent {
    data class GetFreudScore(val records: List<MoodRecordResource>) : FreudScoreIntent
}