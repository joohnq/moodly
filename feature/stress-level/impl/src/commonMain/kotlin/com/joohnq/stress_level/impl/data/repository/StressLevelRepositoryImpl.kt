package com.joohnq.stress_level.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.stress_level.api.converter.StressLevelRecordConverter
import com.joohnq.stress_level.api.converter.StressorsConverter
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class StressLevelRepositoryImpl(
    private val database: StressLevelDatabaseSql,
) : StressLevelRepository {
    private val query = database.stressLevelRecordQueries

    override fun observe(): Flow<List<StressLevelRecord>> =
        query
            .getStressLevels { id, stressLevel, stressors, createdAt ->
                StressLevelRecord(
                    id = id.toInt(),
                    stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                    stressors = StressorsConverter.toStressorsList(stressors),
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(stressLevelRecord: StressLevelRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addStressLevel(
                stressLevel = StressLevelRecordConverter.fromStressLevel(stressLevelRecord.stressLevel),
                stressors = StressorsConverter.fromStressorsList(stressLevelRecord.stressors)
            )
            true
        }

    override suspend fun delete(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteRecord(id = id.toLong())
            true
        }
}
