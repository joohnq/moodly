package com.joohnq.mood.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.converter.StatsRecordConverter
import com.joohnq.mood.domain.data_source.StatsDataSource
import com.joohnq.mood.domain.entity.StatsRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

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

    override suspend fun addStats(statsRecord: StatsRecord) =
        withContext(Dispatchers.IO) {
            query.addStats(
                mood = StatsRecordConverter.fromMood(statsRecord.mood),
                description = statsRecord.description
            )
        }

    override suspend fun deleteStat(id: Int) =
        withContext(Dispatchers.IO) {
            query.deleteStat(id.toLong())
        }
}