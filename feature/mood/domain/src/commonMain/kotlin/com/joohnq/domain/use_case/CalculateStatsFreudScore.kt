package com.joohnq.domain.use_case

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.freud_score.domain.entity.FreudScore

class CalculateStatsFreudScore {
    operator fun invoke(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }
}
