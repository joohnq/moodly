package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.shared.domain.toResult

class UpdateHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(healthJournal: HealthJournalRecord): Result<Boolean> =
        healthJournalRepository.updateHealthJournal(healthJournal).toResult()
}
