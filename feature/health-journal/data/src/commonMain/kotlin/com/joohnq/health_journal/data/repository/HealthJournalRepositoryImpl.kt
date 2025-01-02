package com.joohnq.health_journal.data.repository

import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.shared.domain.DatetimeProvider


class HealthJournalRepositoryImpl(
    private val dataSource: HealthJournalDataSource,
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        dataSource.getHealthJournals()

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ): Boolean =
        try {
            dataSource.addHealthJournal(
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
            dataSource.deleteHealthJournal(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        try {
            dataSource.updateHealthJournal(healthJournal)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}