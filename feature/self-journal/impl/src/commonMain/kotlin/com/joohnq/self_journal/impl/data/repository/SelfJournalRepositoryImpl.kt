package com.joohnq.self_journal.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateTimeMapper.toLocalDateTime
import com.joohnq.mood.api.mapper.MoodMapper.toMood
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SelfJournalRepositoryImpl(
    private val database: AppDatabaseSql,
) : SelfJournalRepository {
    private val query = database.selfJournalsQueries

    override fun observe(): Flow<List<SelfJournalRecord>> =
        query
            .getAll(selfJournalsMapper)
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun getById(id: Long): SelfJournalRecord =
        withContext(Dispatchers.IO) {
            query
                .getById(
                    id = id,
                    mapper = selfJournalsMapper
                ).executeAsOne()
        }

    override suspend fun add(record: SelfJournalRecord) {
        withContext(Dispatchers.IO) {
            query.add(
                mood = record.mood.id,
                title = record.title,
                description = record.description
            )
        }
    }

    override suspend fun deleteById(id: Long) {
        withContext(Dispatchers.IO) {
            query.deleteById(id = id)
        }
    }

    override suspend fun update(record: SelfJournalRecord) {
        withContext(Dispatchers.IO) {
            query.update(
                mood = record.mood.id,
                title = record.title,
                description = record.description,
                id = record.id
            )
        }
    }
}

val selfJournalsMapper: (Long, Long, String, String, String) -> SelfJournalRecord =
    { id, mood, title, description, createdAt ->
        SelfJournalRecord(
            id = id,
            mood = mood.toMood(),
            title = title,
            description = description,
            createdAt = createdAt.toLocalDateTime()
        )
    }
