package com.joohnq.self_journal.domain.use_case

import com.joohnq.mood.domain.entity.MoodAverage
import com.joohnq.mood.domain.mapper.toAverage
import com.joohnq.self_journal.domain.entity.SelfJournalRecord

class CalculateSelfJournalsAverageUseCase {
    operator fun invoke(items: List<SelfJournalRecord>): MoodAverage {
        if (items.isEmpty()) return MoodAverage.Skipped
        val score = items.sumOf { it.mood.healthLevel } / items.size
        return score.toAverage()
    }
}
