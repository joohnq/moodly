package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDateTime

class GetHealthJournalsInYearUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        date: LocalDateTime = dateTimeProvider.getCurrentDateTime(),
        yearsDay: Int = dateTimeProvider.getDaysInYear(date.year),
        healthJournals: List<HealthJournalRecord?>,
    ): String {
        val days =
            healthJournals.filter { it?.date?.year == date.year }
                .associateBy { it?.date?.date }.keys.size
        return "$days/$yearsDay"
    }
}