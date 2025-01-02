package com.joohnq.health_journal.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
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
        executeTryCatchPrinting {
            dataSource.addHealthJournal(
                healthJournalRecord.copy(
                    date = DatetimeProvider.getCurrentDateTime(),
                )
            )
        }

    override suspend fun deleteHealthJournal(id: Int): Boolean =
        executeTryCatchPrinting {
            dataSource.deleteHealthJournal(id)
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        executeTryCatchPrinting {
            dataSource.updateHealthJournal(healthJournal)
        }
}