package com.joohnq.sleep_quality.impl.data.repository

import com.joohnq.api.mapper.StringMapper.toTime
import com.joohnq.api.mapper.TimeMapper.toFormattedTimeString
import com.joohnq.database.SqliteOperationResult
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.database.sqliteExceptionMapper
import com.joohnq.sleep_quality.api.converter.SleepQualityRecordConverter
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.exception.SleepQualityException
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepQualityRepositoryImpl(
    private val database: SleepQualityDatabaseSql,
) : SleepQualityRepository {
    private val query = database.sleepQualityRecordQueries

    private val _records =
        MutableStateFlow<Result<List<SleepQualityRecord>>>(Result.success(listOf()))

    override val records: StateFlow<Result<List<SleepQualityRecord>>> = _records.asStateFlow()

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        repositoryScope.launch {
            getAll()
        }
    }

    override suspend fun getAll(): Result<List<SleepQualityRecord>> {
        val result =
            executeTryCatchResult {
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
                    }.executeAsList()
            }
        _records.value = result
        return result
    }

    override suspend fun add(record: SleepQualityRecord): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                query.addSleepQuality(
                    sleepQuality = SleepQualityRecordConverter.fromSleepQuality(record.sleepQuality),
                    startSleeping = record.startSleeping.toFormattedTimeString(),
                    endSleeping = record.endSleeping.toFormattedTimeString(),
                    sleepInfluencess = SleepQualityRecordConverter.fromInfluences(record.sleepInfluences)
                )
                Result.success(true)
            } catch (e: Exception) {
                val res = sqliteExceptionMapper.map(e)
                when (res.opResult) {
                    SqliteOperationResult.CONSTRAINT -> Result.failure(SleepQualityException.AlreadyBeenAddedToday)
                    else -> Result.failure(Exception(res.cause))
                }
            }
        }

    override suspend fun delete(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteSleepQuality(id = id.toLong())
            true
        }
}
