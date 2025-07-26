package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository

class UpdateSelfJournalsUseCase(
    private val selfJournalRepository: SelfJournalRepository,
) {
    suspend operator fun invoke(selfJournal: SelfJournalRecord): Result<Boolean> = selfJournalRepository.updateSelfJournal(selfJournal)
}
