package com.joohnq.self_journal.api.repository

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import kotlinx.coroutines.flow.Flow

interface SelfJournalRepository {
    fun observe(): Flow<List<SelfJournalRecord>>

    suspend fun getById(id: Long): SelfJournalRecord

    suspend fun add(record: SelfJournalRecord)

    suspend fun deleteById(id: Long)

    suspend fun update(record: SelfJournalRecord)
}
