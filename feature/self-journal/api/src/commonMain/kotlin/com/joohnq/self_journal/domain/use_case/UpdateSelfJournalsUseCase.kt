package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository

class UpdateSelfJournalsUseCase(private val selfJournalRepository: SelfJournalRepository) {
    suspend operator fun invoke(selfJournal: SelfJournalRecord): Result<Boolean> =
        selfJournalRepository.updateSelfJournal(selfJournal)
}
