package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository

class AddSelfJournalsUseCase(
    private val selfJournalRepository: SelfJournalRepository
) {
    suspend operator fun invoke(value: SelfJournalRecord): Result<Boolean> = selfJournalRepository.addSelfJournal(value)
}
