package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.data.DatabaseConstants
import kotlinx.datetime.LocalDateTime

@Dao
interface HealthJournalRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} (mood, title, description, date) VALUES (:mood, :title, :description, :date)")
    suspend fun addHealthJournal(
        mood: Mood,
        title: String,
        description: String,
        date: LocalDateTime = DatetimeManager.getCurrentDateTime()
    )

    @Query("SELECT * FROM ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} ORDER BY date DESC")
    suspend fun getHealthJournals(): List<HealthJournalRecord>

    @Query("DELETE FROM ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} WHERE id = :id")
    suspend fun deleteHealthJournal(id: Int)

    @Update
    suspend fun updateHealthJournal(healthJournal: HealthJournalRecord)
}