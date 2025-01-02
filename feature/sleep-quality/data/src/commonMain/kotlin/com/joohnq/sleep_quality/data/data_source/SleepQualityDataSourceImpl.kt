package com.joohnq.sleep_quality.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatch
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.domain.converter.SleepQualityRecordConverter
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.data_source.SleepQualityDataSource

class SleepQualityDataSourceImpl(private val database: SleepQualityDatabaseSql) :
    SleepQualityDataSource {
    private val query = database.sleepQualityRecordQueries
    override suspend fun getSleepQualities(): List<SleepQualityRecord> =
        query.getSleepQualities { id, sleepQuality, startSleeping, endSleeping, sleepInfluences, date ->
            SleepQualityRecord(
                id = id.toInt(),
                sleepQuality = SleepQualityRecordConverter.toSleepQuality(sleepQuality),
                startSleeping = startSleeping,
                endSleeping = endSleeping,
                sleepInfluences = SleepQualityRecordConverter.toInfluences(sleepInfluences),
                date = LocalDateTimeConverter.toLocalDateTime(date)
            )
        }.executeAsList()

    override suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean =
        executeTryCatch {
            query.addSleepQuality(
                sleepQuality = SleepQualityRecordConverter.fromSleepQuality(sleepQualityRecord.sleepQuality),
                startSleeping = sleepQualityRecord.startSleeping,
                endSleeping = sleepQualityRecord.endSleeping,
                sleepInfluencess = SleepQualityRecordConverter.fromInfluences(sleepQualityRecord.sleepInfluences)
            )
        }
}