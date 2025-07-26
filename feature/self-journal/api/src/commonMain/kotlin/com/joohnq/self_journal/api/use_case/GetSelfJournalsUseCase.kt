package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository

class GetSelfJournalsUseCase(
    private val selfJournalRepository: SelfJournalRepository,
) {
    suspend operator fun invoke(): Result<List<SelfJournalRecord>> = selfJournalRepository.getSelfJournals()
}
