package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository

class GetSelfJournalByIdUseCase(
    private val repository: SelfJournalRepository,
) {
    suspend operator fun invoke(id: Int): Result<SelfJournalRecord> = repository.getById(id)
}
