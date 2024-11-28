package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.SleepQualityRecordDAO
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.util.helper.DatetimeManager

interface SleepQualityRepository {
    suspend fun getSleepQualities(): List<SleepQualityRecord>
    suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean
}

class SleepQualityRepositoryImpl(
    private val sleepQualityRecordDAO: SleepQualityRecordDAO
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        sleepQualityRecordDAO.getSleepQualities()

    override suspend fun addSleepQuality(
        sleepQualityRecord: SleepQualityRecord
    ): Boolean =
        try {
            sleepQualityRecordDAO.addSleepQuality(sleepQualityRecord.copy(date = DatetimeManager.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}