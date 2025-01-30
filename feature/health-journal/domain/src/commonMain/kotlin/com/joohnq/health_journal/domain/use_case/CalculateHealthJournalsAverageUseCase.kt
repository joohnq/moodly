package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.MoodAverage
import com.joohnq.mood.domain.mapper.toAverage

class CalculateHealthJournalsAverageUseCase {
    operator fun invoke(items: List<HealthJournalRecord>): MoodAverage {
        if (items.isEmpty()) return MoodAverage.Skipped
        val score = items.sumOf { it.mood.healthLevel } / items.size
        return score.toAverage()
    }
}
