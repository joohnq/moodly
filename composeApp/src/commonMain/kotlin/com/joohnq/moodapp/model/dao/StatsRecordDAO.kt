package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.LocalDateTime

@Dao
interface StatsRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.STATS_RECORD_DATABASE} (mood, description, date) VALUES (:mood,:description, :date)")
    suspend fun addStats(
        mood: Mood,
        description: String,
        date: LocalDateTime = DatetimeManager.getCurrentDateTime()
    )

    @Query("SELECT * FROM ${DatabaseConstants.STATS_RECORD_DATABASE} ORDER BY date DESC")
    suspend fun getStats(): List<StatsRecord>
}