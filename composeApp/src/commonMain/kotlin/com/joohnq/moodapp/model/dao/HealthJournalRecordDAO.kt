package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.model.DatabaseConstants

@Dao
interface HealthJournalRecordDAO {
//    @Query("INSERT INTO ${DatabaseConstants.STATS_RECORD_DATABASE} (mood, description, date) VALUES (:mood,:description, :date)")
//    suspend fun addStats(
//        mood: Mood,
//        description: String,
//        date: LocalDateTime = DatetimeManager.getCurrentDateTime()
//    )

    @Query("SELECT * FROM ${DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE} ORDER BY date DESC")
    suspend fun getHealthJournals(): List<HealthJournalRecord>
}