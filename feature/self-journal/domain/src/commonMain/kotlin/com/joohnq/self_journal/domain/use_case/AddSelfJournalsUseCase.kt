package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository

class AddSelfJournalsUseCase(private val selfJournalRepository: SelfJournalRepository) {
    suspend operator fun invoke(value: SelfJournalRecord): Result<Boolean> =
        selfJournalRepository.addSelfJournal(value)
}


