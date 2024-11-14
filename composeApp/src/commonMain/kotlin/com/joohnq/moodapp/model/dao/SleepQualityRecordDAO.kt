package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.model.DatabaseConstants

@Dao
interface SleepQualityRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} (${DatabaseConstants.SLEEP_QUALITY}, date) VALUES (:sleepQuality, CURRENT_TIMESTAMP)")
    suspend fun addSleepQuality(sleepQuality: SleepQuality)

    @Query("SELECT * FROM ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getSleepQualities(): List<SleepQualityRecord>
}