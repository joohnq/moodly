package com.joohnq.stress_level.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.domain.converter.StressLevelRecordConverter
import com.joohnq.stress_level.domain.converter.StressorsConverter
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import kotlinx.datetime.LocalDate

class StressLevelRepositoryImpl(
    private val database: StressLevelDatabaseSql,
) : StressLevelRepository {
    private val query = database.stressLevelRecordQueries
    override suspend fun getStressLevels(): Result<List<StressLevelRecord>> =
        executeTryCatchResult {
            query.getStressLevels { id, stressLevel, stressors, date ->
                StressLevelRecord(
                    id = id.toInt(),
                    stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                    stressors = StressorsConverter.toStressorsList(stressors),
                    date = LocalDateTimeConverter.toLocalDate(date)
                )
            }.executeAsList()
        }

    override suspend fun getStressLevelByDate(date: LocalDate): Result<StressLevelRecord?> =
        executeTryCatchResult {
            query.getStressLevelByDate(date.toString()) { id, stressLevel, stressors, date ->
                StressLevelRecord(
                    id = id.toInt(),
                    stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                    stressors = StressorsConverter.toStressorsList(stressors),
                    date = LocalDateTimeConverter.toLocalDate(date)
                )
            }.executeAsOneOrNull()
        }

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addStressLevel(
                stressLevel = StressLevelRecordConverter.fromStressLevel(stressLevelRecord.stressLevel),
                stressors = StressorsConverter.fromStressorsList(stressLevelRecord.stressors)
            )
            true
        }
}