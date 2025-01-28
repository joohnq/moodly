package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.getTotalDays
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDateTime

class GetHealthJournalsInYearUseCase {
    operator fun invoke(
        date: LocalDateTime = getNow(),
        healthJournals: List<HealthJournalRecord?>,
    ): String {
        val days =
            healthJournals.filter { it?.createdAt?.year == date.year }
                .associateBy { it?.createdAt?.date }.keys.size
        return "$days/${date.year.getTotalDays()}"
    }
}