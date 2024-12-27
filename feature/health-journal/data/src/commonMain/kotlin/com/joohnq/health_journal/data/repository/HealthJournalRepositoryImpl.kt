package com.joohnq.health_journal.data.repository

import com.joohnq.health_journal.data.dao.HealthJournalRecordDAO
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.mood.util.helper.DatetimeManager

class HealthJournalRepositoryImpl(
    private val healthJournalRecordDAO: HealthJournalRecordDAO
) : HealthJournalRepository {

    override suspend fun getHealthJournals(): List<HealthJournalRecord> =
        healthJournalRecordDAO.getHealthJournals()

    override suspend fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord
    ): Boolean =
        try {
            healthJournalRecordDAO.addHealthJournal(
                healthJournalRecord.copy(
                    date = DatetimeManager.getCurrentDateTime(),
                )
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteHealthJournal(id: Int): Boolean =
        try {
            healthJournalRecordDAO.deleteHealthJournal(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Boolean =
        try {
            healthJournalRecordDAO.updateHealthJournal(healthJournal)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}