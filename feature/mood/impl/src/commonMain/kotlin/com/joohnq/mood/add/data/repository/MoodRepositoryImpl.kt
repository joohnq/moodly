package com.joohnq.mood.add.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateTimeMapper.toLocalDateTime
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.mapper.MoodMapper.toMood
import com.joohnq.mood.api.repository.MoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MoodRepositoryImpl(
    private val database: AppDatabaseSql,
) : MoodRepository {
    private val query = database.moodsQueries

    override fun observe(): Flow<List<MoodRecord>> =
        query
            .getAll(moodsMapper)
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(record: MoodRecord) {
        withContext(Dispatchers.IO) {
            query.add(
                level = record.mood.id,
                description = record.description
            )
        }
    }

    override suspend fun deleteById(id: Long) {
        withContext(Dispatchers.IO) {
            query.deleteById(id)
        }
    }
}

val moodsMapper: (Long, Long, String, String) -> MoodRecord =
    { id, level, description, createdAt ->
        MoodRecord(
            id = id,
            mood = level.toMood(),
            description = description,
            createdAt = createdAt.toLocalDateTime()
        )
    }
