package com.joohnq.health_journal.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.shared.domain.toResult

class HealthJournalRepositoryImpl(
    private val dataSource: HealthJournalDataSource,
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): Result<List<HealthJournalRecord>> =
        dataSource.getHealthJournals().toResult()

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addHealthJournal(
                healthJournalRecord.copy(
                    date = DatetimeProvider.getCurrentDateTime(),
                )
            )
        }

    override suspend fun deleteHealthJournal(id: Int): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.deleteHealthJournal(id)
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateHealthJournal(healthJournal)
        }
}