package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository

class GetHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(): Result<List<HealthJournalRecord>> =
        healthJournalRepository.getHealthJournals()
}

