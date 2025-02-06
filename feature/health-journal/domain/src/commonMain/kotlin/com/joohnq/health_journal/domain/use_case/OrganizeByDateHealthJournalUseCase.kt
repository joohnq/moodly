package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthDays
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class OrganizeByDateHealthJournalUseCase {
    operator fun invoke(
        date: LocalDateTime = getNow(),
        healthJournals: List<HealthJournalRecord>,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val recordsByDay = healthJournals.groupBy { it.createdAt.date }

        return (1..date.toMonthDays()).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}
