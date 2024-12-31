package com.joohnq.sleep_quality.data.repository

import com.joohnq.domain.DatetimeProvider
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityDataSource
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import org.koin.core.annotation.Single

@Single(binds = [SleepQualityRepository::class])
class SleepQualityRepositoryImpl(
    private val sleepQualityDataSource: SleepQualityDataSource,
) : SleepQualityRepository {
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        sleepQualityDataSource.getSleepQualities()

    override suspend fun addSleepQuality(
        sleepQualityRecord: SleepQualityRecord,
    ): Boolean =
        try {
            sleepQualityDataSource.addSleepQuality(sleepQualityRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}