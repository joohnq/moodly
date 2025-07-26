package com.joohnq.stress_level.impl.data.repository

import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.stress_level.api.converter.StressLevelRecordConverter
import com.joohnq.stress_level.api.converter.StressorsConverter
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository
import com.joohnq.stress_level.database.StressLevelDatabaseSql

class StressLevelRepositoryImpl(
    private val database: StressLevelDatabaseSql
) : StressLevelRepository {
    private val query = database.stressLevelRecordQueries

    override suspend fun getRecords(): Result<List<StressLevelRecord>> =
        executeTryCatchResult {
            query
                .getStressLevels { id, stressLevel, stressors, createdAt ->
                    StressLevelRecord(
                        id = id.toInt(),
                        stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                        stressors = StressorsConverter.toStressorsList(stressors),
                        createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                    )
                }.executeAsList()
        }

    override suspend fun addRecord(stressLevelRecord: StressLevelRecord): Result<Boolean> =
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
