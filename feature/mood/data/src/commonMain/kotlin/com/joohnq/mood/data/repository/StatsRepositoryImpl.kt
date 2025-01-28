package com.joohnq.mood.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.converter.StatsRecordConverter
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository

class StatsRepositoryImpl(
    private val database: StatsDatabaseSql,
) : StatsRepository {
    private val query = database.statRecordQueries
    override suspend fun getStats(): Result<List<StatsRecord>> =
        executeTryCatchResult {
            query.getStats { id, mood, description, createdAt ->
                StatsRecord(
                    id = id.toInt(),
                    mood = StatsRecordConverter.toMood(mood),
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.executeAsList()
        }

    override suspend fun addStats(statsRecord: StatsRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addStats(
                mood = StatsRecordConverter.fromMood(statsRecord.mood),
                description = statsRecord.description
            )
            true
        }

    override suspend fun deleteStat(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteStat(id.toLong())
            true
        }
}