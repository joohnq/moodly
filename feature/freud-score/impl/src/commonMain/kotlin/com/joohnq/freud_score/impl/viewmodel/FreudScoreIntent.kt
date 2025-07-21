package com.joohnq.freud_score.impl.viewmodel

import com.joohnq.mood.ui.resource.MoodRecordResource

sealed interface FreudScoreIntent {
    data class GetFreudScore(val records: List<MoodRecordResource>) : FreudScoreIntent
}