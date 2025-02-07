package com.joohnq.self_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthDays
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class OrganizeByDateSelfJournalUseCase {
    operator fun invoke(
        date: LocalDateTime = getNow(),
        selfJournals: List<SelfJournalRecord>,
    ): Map<LocalDate, List<SelfJournalRecord>?> {
        val recordsByDay = selfJournals.groupBy { it.createdAt.date }

        return (1..date.toMonthDays()).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}
