package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.repository.HealthJournalRepository

class DeleteHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(id: Int): Boolean = healthJournalRepository.deleteHealthJournal(id)
}
