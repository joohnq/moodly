package com.joohnq.health_journal.domain.use_case

import com.joohnq.domain.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDateTime

class GetHealthJournalsInYearUseCase {
    operator fun invoke(
        date: LocalDateTime,
        healthJournals: List<HealthJournalRecord?>,
        dateTimeProvider: IDatetimeProvider
    ): String {
        val yearsDay = dateTimeProvider.getDaysInYear(date.year)
        val days =
            healthJournals.associateBy { it?.let { dateTimeProvider.formatDate(date.date) } }.keys.size
        return "$days/$yearsDay"
    }
}
