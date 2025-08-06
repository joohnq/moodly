package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository
import kotlinx.coroutines.flow.Flow

class GetSelfJournalsUseCase(
    private val repository: SelfJournalRepository,
) {
    operator fun invoke(): Flow<List<SelfJournalRecord>> = repository.observe()
}
