package com.joohnq.health_journal.domain.use_case

import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteHealthJournalsUseCase(private val healthJournalRepository: HealthJournalRepository) {
    suspend operator fun invoke(id: Int): Boolean = healthJournalRepository.deleteHealthJournal(id)
}
