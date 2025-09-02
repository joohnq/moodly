package com.joohnq.sleep_quality.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.api.mapper.StringMapper.toTime
import com.joohnq.api.mapper.TimeMapper.toFormattedTimeString
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateMapper.toLocalDate
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.mapper.SleepInfluencesMapper.mapToIds
import com.joohnq.sleep_quality.api.mapper.SleepInfluencesMapper.toInfluence
import com.joohnq.sleep_quality.api.mapper.SleepQualityMapper.toSleepQuality
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SleepQualityRepositoryImpl(
    private val database: AppDatabaseSql,
) : SleepQualityRepository {
    private val query = database.sleepQualitiesQueries

    override fun observe(): Flow<List<SleepQualityRecord>> =
        query
            .getAll(sleepQualityMapper)
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(record: SleepQualityRecord) {
        withContext(Dispatchers.IO) {
            query.add(
                quality = record.quality.id,
                start = record.start.toFormattedTimeString(),
                end = record.end.toFormattedTimeString(),
                influences = record.influences.mapToIds()
            )
        }
    }

    override suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            query.deleteById(id = id)
        }
    }
}

val sleepQualityMapper: (Long, Long, String, String, List<String>, String) -> SleepQualityRecord =
    { id, quality, start, end, influences, createdAt ->
        SleepQualityRecord(
            id = id,
            quality = quality.toSleepQuality(),
            start = start.toTime(),
            end = end.toTime(),
            influences = influences.toInfluence(),
            createdAt = createdAt.toLocalDate()
        )
    }
