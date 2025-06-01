package com.joohnq.self_journal.domain.use_case

import com.joohnq.self_journal.domain.repository.SelfJournalRepository

class DeleteSelfJournalsUseCase(private val selfJournalRepository: SelfJournalRepository) {
    suspend operator fun invoke(item: Int): Result<Boolean> =
        selfJournalRepository.deleteSelfJournal(item)
}
