package com.joohnq.sleep_quality.data.repository

import com.joohnq.core.database.SqliteOperationResult
import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.core.database.sqliteExceptionMapper
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.domain.converter.SleepQualityRecordConverter
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SleepQualityRepositoryImpl(
    private val database: SleepQualityDatabaseSql,
) : SleepQualityRepository {
    private val query = database.sleepQualityRecordQueries
    override suspend fun getSleepQualities(): Result<List<SleepQualityRecord>> =
        executeTryCatchResult {
            query.getSleepQualities { id, sleepQuality, startSleeping, endSleeping, sleepInfluences, createdAt ->
                SleepQualityRecord(
                    id = id.toInt(),
                    sleepQuality = SleepQualityRecordConverter.toSleepQuality(sleepQuality),
                    startSleeping = DatetimeProvider.getTimeInString(startSleeping),
                    endSleeping = DatetimeProvider.getTimeInString(endSleeping),
                    sleepInfluences = SleepQualityRecordConverter.toInfluences(sleepInfluences),
                    createdAt = LocalDateTimeConverter.toLocalDate(createdAt)
                )
            }.executeAsList()
        }

    override suspend fun addSleepQuality(
        sleepQualityRecord: SleepQualityRecord,
    ): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                query.addSleepQuality(
                    sleepQuality = SleepQualityRecordConverter.fromSleepQuality(sleepQualityRecord.sleepQuality),
                    startSleeping = DatetimeProvider.getTimeString(sleepQualityRecord.startSleeping),
                    endSleeping = DatetimeProvider.getTimeString(sleepQualityRecord.endSleeping),
                    sleepInfluencess = SleepQualityRecordConverter.fromInfluences(sleepQualityRecord.sleepInfluences)
                )
                Result.success(true)
            } catch (e: Exception) {
                val res = sqliteExceptionMapper.map(e)
                when (res.opResult) {
                    SqliteOperationResult.CONSTRAINT -> Result.failure(Exception("A sleep quality record has already been added for today."))
                    else -> Result.failure(Exception(res.cause?.message.toString()))
                }
            }
        }
}