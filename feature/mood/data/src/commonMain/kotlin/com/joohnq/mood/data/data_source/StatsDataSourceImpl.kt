package com.joohnq.mood.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.mood.database.StatRecord
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.StatsRecordConverter
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsDataSource
import org.koin.core.annotation.Single

@Single(binds = [StatsDataSource::class])
class StatsDataSourceImpl(private val database: StatsDatabaseSql) : StatsDataSource {
    private val query = database.statRecordQueries
    override suspend fun getStats(): List<StatsRecord> =
        query.getStats().executeAsList().map { statsRecord: StatRecord ->
            StatsRecord(
                id = statsRecord.id.toInt(),
                mood = StatsRecordConverter.toMood(statsRecord.mood),
                description = statsRecord.description,
                date = LocalDateTimeConverter.toLocalDateTime(statsRecord.date)
            )
        }

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        try {
            query.addStats(
                id = statsRecord.id.toLong(),
                mood = StatsRecordConverter.fromMood(statsRecord.mood),
                description = statsRecord.description
            )
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun deleteStat(id: Int): Boolean =
        try {
            query.deleteStat(id.toLong())
            true
        } catch (e: Exception) {
            false
        }
}