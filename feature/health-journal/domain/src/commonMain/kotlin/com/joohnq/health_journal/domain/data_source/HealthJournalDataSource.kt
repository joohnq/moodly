package com.joohnq.health_journal.domain.data_source

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

interface HealthJournalDataSource {
    suspend fun getHealthJournals(): List<HealthJournalRecord>
    suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord)
    suspend fun deleteHealthJournal(id: Int)
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord)
}