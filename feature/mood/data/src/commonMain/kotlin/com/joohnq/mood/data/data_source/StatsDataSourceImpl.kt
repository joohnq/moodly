package com.joohnq.mood.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.StatsRecordConverter
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsDataSource


class StatsDataSourceImpl(private val database: StatsDatabaseSql) : StatsDataSource {
    private val query = database.statRecordQueries
    override suspend fun getStats(): List<StatsRecord> =
        query.getStats { id, mood, description, date ->
            StatsRecord(
                id = id.toInt(),
                mood = StatsRecordConverter.toMood(mood),
                description = description,
                date = LocalDateTimeConverter.toLocalDateTime(date)
            )
        }.executeAsList()

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