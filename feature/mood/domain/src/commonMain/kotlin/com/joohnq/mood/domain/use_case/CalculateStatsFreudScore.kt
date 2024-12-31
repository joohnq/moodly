package com.joohnq.mood.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.mood.domain.entity.StatsRecord
import org.koin.core.annotation.Factory

@Factory
class CalculateStatsFreudScore {
    operator fun invoke(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }
}
