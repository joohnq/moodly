package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.converters.StressorsConverter
import com.joohnq.moodapp.data.dao.HealthJournalRecordDAO
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.Stressor

interface HealthJournalRepository {
    suspend fun getHealthJournals(): List<HealthJournalRecord>
    suspend fun addHealthJournal(
        title: String,
        description: String,
        mood: Mood,
        stressLevel: StressLevel,
        stressors: List<Stressor>
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
        title: String,
        description: String,
        mood: Mood,
        stressLevel: StressLevel,
        stressors: List<Stressor>
    ): Boolean =
        try {
            healthJournalRecordDAO.addHealthJournal(
                mood = mood,
                title = title,
                description = description,
                stressLevel = stressLevel,
                stressors = StressorsConverter().fromStressorsList(stressors)
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