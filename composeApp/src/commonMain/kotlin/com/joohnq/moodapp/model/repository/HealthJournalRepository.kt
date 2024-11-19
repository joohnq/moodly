package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.model.dao.HealthJournalRecordDAO

interface HealthJournalRepository {
    suspend fun getHealthJournals(): List<HealthJournalRecord>
}

class HealthJournalRepositoryImpl(
    private val healthJournalRecordDAO: HealthJournalRecordDAO
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        healthJournalRecordDAO.getHealthJournals()
}