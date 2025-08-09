package com.joohnq.mood.add.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.mood.api.converter.MoodRecordConverter
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MoodRepositoryImpl(
    private val database: MoodDatabaseSql,
) : MoodRepository {
    private val query = database.moodRecordQueries

    override fun observe(): Flow<List<MoodRecord>> =
        query
            .getMoodRecords { id, mood, description, createdAt ->
                MoodRecord(
                    id = id.toInt(),
                    mood = MoodRecordConverter.toMood(mood),
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(record: MoodRecord) {
        withContext(Dispatchers.IO) {
            query.addMoodRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                description = record.description
            )
        }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            query.deleteMoodRecord(id.toLong())
            true
        }
    }
}
