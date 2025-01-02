package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.shared.domain.toResult

class GetHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(): Result<List<HealthJournalRecord>> =
        healthJournalRepository.getHealthJournals().toResult()
}

