package com.joohnq.health_journal.domain.repository

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate

interface HealthJournalRepository {
    suspend fun getHealthJournals(): Result<List<HealthJournalRecord>>
    suspend fun getHealthJournalByDate(date: LocalDate): Result<HealthJournalRecord?>
    suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord): Result<Boolean>
    suspend fun deleteHealthJournal(id: Int): Result<Boolean>
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Result<Boolean>
}