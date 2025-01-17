package com.joohnq.health_journal.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.mood.domain.converter.StatsRecordConverter

class HealthJournalRepositoryImpl(
    private val database: HealthJournalDatabaseSql,
) : HealthJournalRepository {
    private val query = database.healthJournalRecordQueries
    override suspend fun getHealthJournals(): Result<List<HealthJournalRecord>> =
        executeTryCatchResult {
            query.getHealthJournals { id, mood, title, description, createdAt ->
                HealthJournalRecord(
                    id = id.toInt(),
                    mood = StatsRecordConverter.toMood(mood),
                    title = title,
                    description = description,
                    createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                )
            }.executeAsList()
        }

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ): Result<Boolean> =
        executeTryCatchResult {
            query.addHealthJournal(
                mood = StatsRecordConverter.fromMood(healthJournalRecord.mood),
                title = healthJournalRecord.title,
                description = healthJournalRecord.description,
            )
            true
        }

    override suspend fun deleteHealthJournal(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteHealthJournal(
                id = id.toLong()
            )
            true
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Result<Boolean> =
        executeTryCatchResult {
            query.updateHealthJournal(
                mood = StatsRecordConverter.fromMood(healthJournal.mood),
                title = healthJournal.title,
                description = healthJournal.description,
                id = healthJournal.id.toLong()
            )
            true
        }
}