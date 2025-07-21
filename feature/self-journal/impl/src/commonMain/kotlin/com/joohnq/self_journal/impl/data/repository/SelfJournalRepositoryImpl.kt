package com.joohnq.self_journal.impl.data.repository

import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.mood.domain.converter.MoodRecordConverter
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository

class SelfJournalRepositoryImpl(
    private val database: SelfJournalDatabaseSql,
) : SelfJournalRepository {
    private val query = database.selfJournalRecordQueries
    override suspend fun getSelfJournals(): Result<List<SelfJournalRecord>> =
        executeTryCatchResult {
            query.getSelfJournalRecords { id, mood, title, description, createdAt ->
                SelfJournalRecord(
                    id = id.toInt(),
                    mood = MoodRecordConverter.toMood(mood),
                    title = title,
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.executeAsList()
        }

    override suspend fun addSelfJournal(
        record: SelfJournalRecord,
    ): Result<Boolean> =
        executeTryCatchResult {
            query.addSelfJournalRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                title = record.title,
                description = record.description,
            )
            true
        }

    override suspend fun deleteSelfJournal(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteSelfJournalRecord(
                id = id.toLong()
            )
            true
        }

    override suspend fun updateSelfJournal(record: SelfJournalRecord): Result<Boolean> =
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