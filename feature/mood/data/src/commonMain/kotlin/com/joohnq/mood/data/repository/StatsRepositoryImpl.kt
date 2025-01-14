package com.joohnq.mood.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.converter.StatsRecordConverter
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import kotlinx.datetime.LocalDate

class StatsRepositoryImpl(
    private val database: StatsDatabaseSql,
) : StatsRepository {
    private val query = database.statRecordQueries
    override suspend fun getStats(): Result<List<StatsRecord>> =
        executeTryCatchResult {
            query.getStats { id, mood, description, date ->
                StatsRecord(
                    id = id.toInt(),
                    mood = StatsRecordConverter.toMood(mood),
                    description = description,
                    date = LocalDateTimeConverter.toLocalDate(date)
                )
            }.executeAsList()
        }

    override suspend fun getStatByDate(date: LocalDate): Result<StatsRecord?> =
        executeTryCatchResult {
            query.getStatByDate(date.toString()) { id, mood, description, date ->
                StatsRecord(
                    id = id.toInt(),
                    mood = StatsRecordConverter.toMood(mood),
                    description = description,
                    date = LocalDateTimeConverter.toLocalDate(date)
                )
            }.executeAsOneOrNull()
        }

    override suspend fun addStats(statsRecord: StatsRecord): Result<Boolean> =
        executeTryCatchResult {
            val item = statsRecord.copy(date = DatetimeProvider.getCurrentDateTime().date)
            query.addStats(
                mood = StatsRecordConverter.fromMood(item.mood),
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