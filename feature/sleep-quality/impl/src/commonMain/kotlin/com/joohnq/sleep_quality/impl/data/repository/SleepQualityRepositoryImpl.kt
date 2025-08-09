package com.joohnq.sleep_quality.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.api.mapper.StringMapper.toTime
import com.joohnq.api.mapper.TimeMapper.toFormattedTimeString
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.sleep_quality.api.converter.SleepQualityRecordConverter
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SleepQualityRepositoryImpl(
    private val database: SleepQualityDatabaseSql,
) : SleepQualityRepository {
    private val query = database.sleepQualityRecordQueries

    override fun observe(): Flow<List<SleepQualityRecord>> =
        query
            .getSleepQualities { id, sleepQuality, startSleeping, endSleeping, sleepInfluences, createdAt ->
                SleepQualityRecord(
                    id = id.toInt(),
                    sleepQuality = SleepQualityRecordConverter.toSleepQuality(sleepQuality),
                    startSleeping = startSleeping.toTime(),
                    endSleeping = endSleeping.toTime(),
                    sleepInfluences = SleepQualityRecordConverter.toInfluences(sleepInfluences),
                    createdAt = LocalDateTimeConverter.toLocalDate(createdAt)
                )
            }.asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(record: SleepQualityRecord) {
        withContext(Dispatchers.IO) {
            query.addSleepQuality(
                sleepQuality = SleepQualityRecordConverter.fromSleepQuality(record.sleepQuality),
                startSleeping = record.startSleeping.toFormattedTimeString(),
                endSleeping = record.endSleeping.toFormattedTimeString(),
                sleepInfluencess = SleepQualityRecordConverter.fromInfluences(record.sleepInfluences)
            )
        }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            query.deleteSleepQuality(id = id.toLong())
        }
    }
}
