package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.annotation.Factory

@Factory
class GetHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(): List<HealthJournalRecord> =
        healthJournalRepository.getHealthJournals()
}
