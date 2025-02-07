package com.joohnq.self_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

class OrganizeFromCreationSelfJournalFreudScoreUseCase {
    operator fun invoke(
        creationAt: LocalDate = getNow().date,
        selfJournals: List<SelfJournalRecord>,
    ): Map<LocalDate, List<SelfJournalRecord>?> {
        val now = getNow().date
        val recordsByDay = selfJournals.groupBy { it.createdAt.date }
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