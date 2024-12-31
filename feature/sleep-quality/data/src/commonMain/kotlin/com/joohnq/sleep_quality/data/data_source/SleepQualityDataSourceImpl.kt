package com.joohnq.sleep_quality.data.data_source

import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityDataSource
import org.koin.core.annotation.Single

@Single(binds = [SleepQualityDataSource::class])
class SleepQualityDataSourceImpl(private val database: SleepQualityDatabase) :
    SleepQualityDataSource {
    private val query = database.statRecordQueries
    override suspend fun getSleepQualities(): List<SleepQualityRecord> {
        TODO("Not yet implemented")
    }

    override suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean {
        TODO("Not yet implemented")
    }

}