package com.joohnq.health_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

class OrganizeFromCreationHealthJournalFreudScoreUseCase {
    operator fun invoke(
        creationAt: LocalDate = getNow().date,
        healthJournals: List<HealthJournalRecord>,
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val now = getNow().date
        val recordsByDay = healthJournals.groupBy { it.createdAt.date }
        val dateSequence = generateDateSequence(creationAt, now)
        return dateSequence.associateWith { date -> recordsByDay[date] }
    }

    private fun generateDateSequence(
        creationDate: LocalDate,
        currentDate: LocalDate,
    ): Sequence<LocalDate> {
        return generateSequence(creationDate) { current ->
            val nextDate = current.plus(1, DateTimeUnit.DAY)
            if (nextDate <= currentDate) nextDate else null
        }
    }
}