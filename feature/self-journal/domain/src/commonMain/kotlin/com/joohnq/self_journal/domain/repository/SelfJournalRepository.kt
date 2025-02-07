package com.joohnq.self_journal.domain.repository

import com.joohnq.self_journal.domain.entity.SelfJournalRecord

interface SelfJournalRepository {
    suspend fun getSelfJournals(): Result<List<SelfJournalRecord>>
    suspend fun addSelfJournal(record: SelfJournalRecord): Result<Boolean>
    suspend fun deleteSelfJournal(id: Int): Result<Boolean>
    suspend fun updateSelfJournal(selfJournal: SelfJournalRecord): Result<Boolean>
}