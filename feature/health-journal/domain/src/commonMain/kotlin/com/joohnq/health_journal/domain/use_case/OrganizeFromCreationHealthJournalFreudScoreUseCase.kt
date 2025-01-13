package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.IDatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

class OrganizeFromCreationHealthJournalFreudScoreUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        creationDate: LocalDateTime,
        currentDate: LocalDateTime = dateTimeProvider.getCurrentDateTime(),
        healthJournals: List<HealthJournalRecord>,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val recordsByDay = healthJournals.groupBy { it.date.date }
        val dateSequence = generateDateSequence(creationDate, currentDate)
        return dateSequence.associate { date ->
            val localDate = LocalDate(date.year, date.month, date.dayOfMonth)
            localDate to recordsByDay[localDate]
        }
    }

    private fun generateDateSequence(
        creationDate: LocalDateTime,
        currentDate: LocalDateTime,
    ): Sequence<LocalDate> {
        return generateSequence(creationDate.date) { current ->
            val nextDate = current.plus(1, DateTimeUnit.DAY)
            if (nextDate <= currentDate.date) nextDate else null
        }
    }
}