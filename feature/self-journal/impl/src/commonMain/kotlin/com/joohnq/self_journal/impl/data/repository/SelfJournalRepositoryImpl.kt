package com.joohnq.self_journal.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.mood.api.converter.MoodRecordConverter
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SelfJournalRepositoryImpl(
    private val database: SelfJournalDatabaseSql,
) : SelfJournalRepository {
    private val query = database.selfJournalRecordQueries

    override fun observe(): Flow<List<SelfJournalRecord>> =
        query
            .getSelfJournalRecords { id, mood, title, description, createdAt ->
                SelfJournalRecord(
                    id = id.toInt(),
                    mood = MoodRecordConverter.toMood(mood),
                    title = title,
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun getById(id: Int): SelfJournalRecord =
        withContext(Dispatchers.IO) {
            query
                .getSelfJournalByIdRecord(
                    id = id.toLong()
                ) { id, mood, title, description, createdAt ->
                    SelfJournalRecord(
                        id = id.toInt(),
                        mood = MoodRecordConverter.toMood(mood),
                        title = title,
                        description = description,
                        createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                    )
                }.executeAsOne()
        }

    override suspend fun add(record: SelfJournalRecord) {
        withContext(Dispatchers.IO) {
            query.addSelfJournalRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                title = record.title,
                description = record.description
            )
        }
    }

    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            query.deleteSelfJournalRecord(
                id = id.toLong()
            )
        }
    }

    override suspend fun update(record: SelfJournalRecord) {
        withContext(Dispatchers.IO) {
            query.updateSelfJournalRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                title = record.title,
                description = record.description,
                id = record.id.toLong()
            )
        }
    }
}
