package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord

class GetHealthJournalsInYearUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        healthJournals: List<HealthJournalRecord?>,
    ): String {
        val date = dateTimeProvider.getCurrentDateTime()
        val yearsDay = dateTimeProvider.getDaysInYear(date.year)
        val days =
            healthJournals.associateBy { it?.let { dateTimeProvider.formatDate(date.date) } }.keys.size
        return "$days/$yearsDay"
    }
}