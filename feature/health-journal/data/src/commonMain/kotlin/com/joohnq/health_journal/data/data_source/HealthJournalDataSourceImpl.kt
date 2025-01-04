package com.joohnq.health_journal.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.converter.StatsRecordConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class HealthJournalDataSourceImpl(private val database: HealthJournalDatabaseSql) :
    HealthJournalDataSource {
    private val query = database.healthJournalRecordQueries
    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        withContext(Dispatchers.IO) {
            query.getHealthJournals { id, mood, title, description, date ->
                HealthJournalRecord(
                    id = id.toInt(),
                    mood = StatsRecordConverter.toMood(mood),
                    title = title,
                    description = description,
                    date = LocalDateTimeConverter.toLocalDateTime(date)
                )
            }.executeAsList()
        }

    override suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord) =
        withContext(Dispatchers.IO) {
            query.addHealthJournal(
                id = healthJournalRecord.id.toLong(),
                mood = StatsRecordConverter.fromMood(healthJournalRecord.mood),
                title = healthJournalRecord.title,
                description = healthJournalRecord.description,
            )
        }

    override suspend fun deleteHealthJournal(id: Int) =
        withContext(Dispatchers.IO) {
            query.deleteHealthJournal(
                id = id.toLong()
            )
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord) =
        withContext(Dispatchers.IO) {
            query.updateHealthJournal(
                mood = StatsRecordConverter.fromMood(healthJournal.mood),
                title = healthJournal.title,
                description = healthJournal.description,
                id = healthJournal.id.toLong()
            )
        }
}