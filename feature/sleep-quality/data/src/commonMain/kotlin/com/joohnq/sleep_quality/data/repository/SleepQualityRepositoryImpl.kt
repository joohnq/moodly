package com.joohnq.sleep_quality.data.repository

import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.sleep_quality.data.dao.SleepQualityRecordDAO
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository

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