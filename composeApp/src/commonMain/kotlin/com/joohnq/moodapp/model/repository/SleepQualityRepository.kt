package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.model.dao.SleepQualityRecordDAO

interface SleepQualityRepository {
    suspend fun getSleepQualities(): List<SleepQualityRecord>
    suspend fun addSleepQuality(sleepQuality: SleepQuality): Boolean
}

class SleepQualityRepositoryImpl(
    private val sleepQualityRecordDAO: SleepQualityRecordDAO
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        sleepQualityRecordDAO.getSleepQualities()

    override suspend fun addSleepQuality(sleepQuality: SleepQuality): Boolean =
        try {
            sleepQualityRecordDAO.addSleepQuality(sleepQuality)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}