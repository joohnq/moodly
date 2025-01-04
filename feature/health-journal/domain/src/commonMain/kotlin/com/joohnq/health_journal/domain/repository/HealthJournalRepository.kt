package com.joohnq.health_journal.domain.repository

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

interface HealthJournalRepository {
    suspend fun getHealthJournals(): Result<List<HealthJournalRecord>>
    suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord): Result<Boolean>
    suspend fun deleteHealthJournal(id: Int): Result<Boolean>
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Result<Boolean>
}