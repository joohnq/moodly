package com.joohnq.self_journal.domain.use_case

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.getTotalDays
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.datetime.LocalDateTime

class GetSelfJournalsInYearUseCase {
    operator fun invoke(
        date: LocalDateTime = getNow(),
        selfJournals: List<SelfJournalRecord?>,
    ): String {
        val days =
            selfJournals.filter { it?.createdAt?.year == date.year }
                .associateBy { it?.createdAt?.date }.keys.size
        return "$days/${date.year.getTotalDays()}"
    }
}