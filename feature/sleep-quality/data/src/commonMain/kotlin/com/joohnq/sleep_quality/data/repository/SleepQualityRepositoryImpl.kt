package com.joohnq.sleep_quality.data.repository

import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityDataSource
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository


class SleepQualityRepositoryImpl(
    private val dataSource: SleepQualityDataSource,
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        dataSource.getSleepQualities()

    override suspend fun addSleepQuality(
        sleepQualityRecord: SleepQualityRecord,
    ): Boolean =
        try {
            dataSource.addSleepQuality(sleepQualityRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}