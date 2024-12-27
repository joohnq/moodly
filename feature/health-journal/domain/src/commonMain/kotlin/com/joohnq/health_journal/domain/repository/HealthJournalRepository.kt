package com.joohnq.health_journal.domain.repository

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

interface HealthJournalRepository {
    suspend fun getHealthJournals(): List<HealthJournalRecord>
    suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord): Boolean
    suspend fun deleteHealthJournal(id: Int): Boolean
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean
}