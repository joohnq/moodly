package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.domain.HealthJournalRecord

@Dao
interface HealthJournalRecordDAO {
    @Insert
    suspend fun addHealthJournal(healthJournal: HealthJournalRecord)

    @Query("SELECT * FROM ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} ORDER BY date DESC")
    suspend fun getHealthJournals(): List<HealthJournalRecord>

    @Query("DELETE FROM ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} WHERE id = :id")
    suspend fun deleteHealthJournal(id: Int)

    @Update
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord)
}