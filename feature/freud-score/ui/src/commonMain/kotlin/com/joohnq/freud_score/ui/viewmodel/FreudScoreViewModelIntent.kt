package com.joohnq.freud_score.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord

sealed class FreudScoreViewModelIntent {
    data class GetFreudScore(val statsRecords: List<StatsRecord>) : FreudScoreViewModelIntent()
}