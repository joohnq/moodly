package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.data.DatabaseConstants
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