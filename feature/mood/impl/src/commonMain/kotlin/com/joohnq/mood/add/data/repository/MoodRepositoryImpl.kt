package com.joohnq.mood.add.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.mood.api.converter.MoodRecordConverter
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

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

    override suspend fun add(record: MoodRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addMoodRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                description = record.description
            )
            true
        }

    override suspend fun delete(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteMoodRecord(id.toLong())
            true
        }
}
