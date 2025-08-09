package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository

class AddSelfJournalsUseCase(
    private val repository: SelfJournalRepository,
) {
    suspend operator fun invoke(value: SelfJournalRecord): Result<Unit> =
        try {
            repository.add(value)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
