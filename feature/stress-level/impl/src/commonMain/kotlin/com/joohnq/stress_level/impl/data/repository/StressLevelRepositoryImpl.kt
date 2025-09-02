package com.joohnq.stress_level.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateTimeMapper.toLocalDateTime
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.mapper.StressLevelMapper.toStressLevel
import com.joohnq.stress_level.api.mapper.StressorMapper.join
import com.joohnq.stress_level.api.mapper.StressorMapper.toStressors
import com.joohnq.stress_level.api.repository.StressLevelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class StressLevelRepositoryImpl(
    private val database: AppDatabaseSql,
) : StressLevelRepository {
    private val query = database.stressLevelsQueries

    override fun observe(): Flow<List<StressLevelRecord>> =
        query
            .getAll(stressLevelMapper)
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(stressLevelRecord: StressLevelRecord) {
        withContext(Dispatchers.IO) {
            query.add(
                level = stressLevelRecord.level.id,
                stressors = stressLevelRecord.stressors.join()
            )
        }
    }

    override suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            query.deleteById(id = id)
        }
    }
}

val stressLevelMapper: (Long, Long, List<String>, String) -> StressLevelRecord =
    { id, level, stressors, createdAt ->
        StressLevelRecord(
            id = id,
            level = level.toStressLevel(),
            stressors = stressors.toStressors(),
            createdAt = createdAt.toLocalDateTime()
        )
    }
