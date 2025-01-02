package com.joohnq.health_journal.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository

class CalculateHealthJournalFreudScoreUseCase(private val healthJournalRepository: HealthJournalRepository) {
    operator fun invoke(items: List<HealthJournalRecord>): FreudScore? {
        if (items.isEmpty()) return null
        val score = items.sumOf { it.mood.healthLevel } / items.size
        return FreudScore.fromScore(score)
    }
}
