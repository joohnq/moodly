package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.converters.StressorsConverter
import com.joohnq.moodapp.data.dao.HealthJournalRecordDAO
import com.joohnq.moodapp.domain.HealthJournalRecord

interface HealthJournalRepository {
    suspend fun getHealthJournals(): List<HealthJournalRecord>
    suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord
    ): Boolean

    suspend fun deleteHealthJournal(id: Int): Boolean
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean
}

class HealthJournalRepositoryImpl(
    private val healthJournalRecordDAO: HealthJournalRecordDAO
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        healthJournalRecordDAO.getHealthJournals()

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord
    ): Boolean =
        try {
            healthJournalRecordDAO.addHealthJournal(
                mood = healthJournalRecord.mood,
                title = healthJournalRecord.title,
                description = healthJournalRecord.description,
                stressLevel = healthJournalRecord.stressLevel,
                stressors = StressorsConverter().fromStressorsList(healthJournalRecord.stressors)
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteHealthJournal(id: Int): Boolean =
        try {
            healthJournalRecordDAO.deleteHealthJournal(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        try {
            healthJournalRecordDAO.updateHealthJournal(healthJournal)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}