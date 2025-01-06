package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate

class OrganizeByDateHealthJournalUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        healthJournals: List<HealthJournalRecord>,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val currentDate = dateTimeProvider.getCurrentDateTime()
        val monthDaysCount = dateTimeProvider.getMonthDaysCount(currentDate)
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(currentDate.year, currentDate.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}
