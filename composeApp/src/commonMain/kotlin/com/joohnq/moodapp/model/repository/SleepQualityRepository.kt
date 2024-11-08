package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.model.dao.SleepQualityRecordDAO

interface SleepQualityRepository {
    suspend fun getSleepQualities(): List<SleepQualityRecord>
    suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean
}

class SleepQualityRepositoryImpl(
    private val sleepQualityRecordDAO: SleepQualityRecordDAO
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        sleepQualityRecordDAO.getSleepQualities()

    override suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean =
        try {
            sleepQualityRecordDAO.addSleepQuality(sleepQualityRecord)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}