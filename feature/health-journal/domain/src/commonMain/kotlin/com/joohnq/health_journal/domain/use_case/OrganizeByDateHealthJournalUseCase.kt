package com.joohnq.health_journal.domain.use_case

import com.joohnq.domain.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate

class OrganizeByDateHealthJournalUseCase {
    operator fun invoke(
        monthDaysCount: Int,
        healthJournals: List<HealthJournalRecord>,
        dateTimeProvider: IDatetimeProvider,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val currentDate = dateTimeProvider.getCurrentDateTime()
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(currentDate.year, currentDate.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}
