package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository

class GetSelfJournalsUseCase(private val selfJournalRepository: SelfJournalRepository) {
    suspend operator fun invoke(): Result<List<SelfJournalRecord>> =
        selfJournalRepository.getSelfJournals()
}

