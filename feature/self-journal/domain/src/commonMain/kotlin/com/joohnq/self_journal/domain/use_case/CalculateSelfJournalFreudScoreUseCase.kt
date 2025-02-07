package com.joohnq.self_journal.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.domain.mapper.toFreudScore
import com.joohnq.self_journal.domain.entity.SelfJournalRecord

class CalculateSelfJournalFreudScoreUseCase {
    operator fun invoke(items: List<SelfJournalRecord>): FreudScore? {
        if (items.isEmpty()) return null
        val score = items.sumOf { it.mood.healthLevel } / items.size
        return score.toFreudScore()
    }
}
