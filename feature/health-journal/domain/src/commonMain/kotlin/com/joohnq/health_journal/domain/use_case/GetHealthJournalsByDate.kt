package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import kotlinx.datetime.LocalDate

class GetHealthJournalsByDate(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(date: LocalDate): Result<HealthJournalRecord?> =
        healthJournalRepository.getHealthJournalByDate(date)
}
