package com.joohnq.self_journal.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.mood.api.converter.MoodRecordConverter
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.repository.SelfJournalRepository
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

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

    override suspend fun getById(id: Int): Result<SelfJournalRecord> =
        executeTryCatchResult {
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

    override suspend fun add(record: SelfJournalRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addSelfJournalRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                title = record.title,
                description = record.description
            )
            true
        }

    override suspend fun delete(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteSelfJournalRecord(
                id = id.toLong()
            )
            true
        }

    override suspend fun update(record: SelfJournalRecord): Result<Boolean> =
        executeTryCatchResult {
            query.updateSelfJournalRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                title = record.title,
                description = record.description,
                id = record.id.toLong()
            )
            true
        }
}