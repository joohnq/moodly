package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.data.dao.SleepQualityRecordDAO

interface SleepQualityRepository {
    suspend fun getSleepQualities(): List<SleepQualityRecord>
    suspend fun addSleepQuality(
        sleepQuality: SleepQuality,
        startSleeping: String,
        endSleeping: String,
        sleepInfluences: List<SleepInfluences>
    ): Boolean
}

class SleepQualityRepositoryImpl(
    private val sleepQualityRecordDAO: SleepQualityRecordDAO
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        sleepQualityRecordDAO.getSleepQualities()

    override suspend fun addSleepQuality(
        sleepQuality: SleepQuality,
        startSleeping: String,
        endSleeping: String,
        sleepInfluences: List<SleepInfluences>
    ): Boolean =
        try {
            sleepQualityRecordDAO.addSleepQuality(
                sleepQuality = sleepQuality,
                startSleeping = startSleeping,
                endSleeping = endSleeping,
                sleepInfluences = sleepInfluences
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}