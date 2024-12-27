package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository

class UpdateHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(healthJournal: HealthJournalRecord): Boolean =
        healthJournalRepository.updateHealthJournal(healthJournal)
}
