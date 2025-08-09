package com.joohnq.self_journal.api.repository

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import kotlinx.coroutines.flow.Flow

interface SelfJournalRepository {
    fun observe(): Flow<List<SelfJournalRecord>>

    suspend fun getById(id: Int): SelfJournalRecord

    suspend fun add(record: SelfJournalRecord)

    suspend fun delete(id: Int)

    suspend fun update(record: SelfJournalRecord)
}
