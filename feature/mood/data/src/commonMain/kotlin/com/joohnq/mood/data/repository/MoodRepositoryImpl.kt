package com.joohnq.mood.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.converter.MoodRecordConverter
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository

class MoodRepositoryImpl(
    private val database: StatsDatabaseSql,
) : MoodRepository {
    private val query = database.moodRecordQueries
    override suspend fun getMoodRecords(): Result<List<MoodRecord>> =
        executeTryCatchResult {
            query.getMoodRecords { id, mood, description, createdAt ->
                MoodRecord(
                    id = id.toInt(),
                    mood = MoodRecordConverter.toMood(mood),
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.executeAsList()
        }

    override suspend fun addMoodRecord(record: MoodRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addMoodRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                description = record.description
            )
            true
        }

    override suspend fun deleteMoodRecord(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteMoodRecord(id.toLong())
            true
        }
}