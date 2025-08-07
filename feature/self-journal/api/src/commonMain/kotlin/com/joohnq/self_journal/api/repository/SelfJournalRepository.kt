package com.joohnq.self_journal.api.repository

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import kotlinx.coroutines.flow.Flow

interface SelfJournalRepository {
    fun observe(): Flow<List<SelfJournalRecord>>

    suspend fun getById(id: Int): Result<SelfJournalRecord>

    suspend fun add(record: SelfJournalRecord): Result<Boolean>

    suspend fun delete(id: Int): Result<Boolean>

    suspend fun update(record: SelfJournalRecord): Result<Boolean>
}
