package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.repository.HealthJournalRepository

class DeleteHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(item: Int): Result<Boolean> =
        healthJournalRepository.deleteHealthJournal(item)
}
