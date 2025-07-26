package com.joohnq.self_journal.api.repository

import com.joohnq.self_journal.api.entity.SelfJournalRecord

interface SelfJournalRepository {
    suspend fun getSelfJournals(): Result<List<SelfJournalRecord>>

    suspend fun addSelfJournal(record: SelfJournalRecord): Result<Boolean>

    suspend fun deleteSelfJournal(id: Int): Result<Boolean>

    suspend fun updateSelfJournal(record: SelfJournalRecord): Result<Boolean>
}
