package com.joohnq.health_journal.data.repository

import com.joohnq.domain.DatetimeProvider
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.annotation.Single

@Single(binds = [HealthJournalRepository::class])
class HealthJournalRepositoryImpl(
    private val healthJournalDataSource: HealthJournalDataSource,
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        healthJournalDataSource.getHealthJournals()

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ): Boolean =
        try {
            healthJournalDataSource.addHealthJournal(
                healthJournalRecord.copy(
                    date = DatetimeProvider.getCurrentDateTime(),
                )
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteHealthJournal(id: Int): Boolean =
        try {
            healthJournalDataSource.deleteHealthJournal(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        try {
            healthJournalDataSource.updateHealthJournal(healthJournal)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}