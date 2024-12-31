package com.joohnq.health_journal.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.healthjournal.database.HealthJournal
import com.joohnq.mood.domain.StatsRecordConverter
import org.koin.core.annotation.Single

@Single(binds = [HealthJournalDataSource::class])
class HealthJournalDataSourceImpl(private val database: HealthJournalDatabaseSql) :
    HealthJournalDataSource {
    private val query = database.healthJournalQueries
    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        query.getHealthJournals().executeAsList().map { healthJournal: HealthJournal ->
            HealthJournalRecord(
                id = healthJournal.id.toInt(),
                mood = StatsRecordConverter.toMood(healthJournal.mood),
                title = healthJournal.title,
                description = healthJournal.description,
                date = LocalDateTimeConverter.toLocalDateTime(healthJournal.date)
            )
        }

    override suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord): Boolean =
        try {
            query.addHealthJournal(
                id = healthJournalRecord.id.toLong(),
                mood = StatsRecordConverter.fromMood(healthJournalRecord.mood),
                title = healthJournalRecord.title,
                description = healthJournalRecord.description,
            )
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun deleteHealthJournal(id: Int): Boolean =
        try {
            query.deleteHealthJournal(
                id = id.toLong()
            )
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        try {
            query.updateHealthJournal(
                mood = StatsRecordConverter.fromMood(healthJournal.mood),
                title = healthJournal.title,
                description = healthJournal.description,
                id = healthJournal.id.toLong()
            )
            true
        } catch (e: Exception) {
            false
        }

}