package com.joohnq.health_journal.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord

class CalculateHealthJournalFreudScoreUseCase {
    operator fun invoke(healthJournalRecords: List<HealthJournalRecord>): FreudScore? {
        if (healthJournalRecords.isEmpty()) return null
        val score = healthJournalRecords.sumOf { it.mood.healthLevel } / healthJournalRecords.size
        return FreudScore.fromScore(score)
    }
}