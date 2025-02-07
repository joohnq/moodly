package com.joohnq.mood.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.domain.mapper.toFreudScore
import com.joohnq.mood.domain.entity.MoodRecord

class CalculateStatsFreudScoreUseCase {
    operator fun invoke(records: List<MoodRecord?>): FreudScore {
        val score = records.sumOf { it?.mood?.healthLevel ?: 0 } / records.size
        return score.toFreudScore()
    }
}
