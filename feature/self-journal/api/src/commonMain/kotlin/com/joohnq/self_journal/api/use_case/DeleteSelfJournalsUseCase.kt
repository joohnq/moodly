package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.repository.SelfJournalRepository

class DeleteSelfJournalsUseCase(
    private val repository: SelfJournalRepository,
) {
    suspend operator fun invoke(item: Int): Result<Unit> =
        try {
            repository.delete(item)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
