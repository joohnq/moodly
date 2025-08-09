package com.joohnq.sleep_quality.api.use_case

import com.joohnq.database.SqliteExceptionMapper
import com.joohnq.database.SqliteOperationResult
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.exception.SleepQualityException
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository

class AddSleepQualityUseCase(
    private val repository: SleepQualityRepository,
    private val sqliteExceptionMapper: SqliteExceptionMapper,
) {
    suspend operator fun invoke(record: SleepQualityRecord): Result<Unit> =
        try {
            repository.add(record)
            Result.success(Unit)
        } catch (e: Exception) {
            val res = sqliteExceptionMapper.map(e)
            when (res.opResult) {
                SqliteOperationResult.CONSTRAINT -> Result.failure(SleepQualityException.AlreadyBeenAddedToday)
                else -> Result.failure(Exception(res.cause))
            }
        }
}
