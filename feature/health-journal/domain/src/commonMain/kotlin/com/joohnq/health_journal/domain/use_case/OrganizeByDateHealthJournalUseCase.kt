package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class OrganizeByDateHealthJournalUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        date: LocalDateTime = dateTimeProvider.getCurrentDateTime(),
        monthDaysCount: Int = dateTimeProvider.getMonthDaysCount(date),
        healthJournals: List<HealthJournalRecord>,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val recordsByDay = healthJournals.groupBy { it.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}
