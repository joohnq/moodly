package com.joohnq.self_journal.api.use_case

import com.joohnq.self_journal.api.repository.SelfJournalRepository

class DeleteSelfJournalsUseCase(
    private val selfJournalRepository: SelfJournalRepository
) {
    suspend operator fun invoke(item: Int): Result<Boolean> = selfJournalRepository.deleteSelfJournal(item)
}
