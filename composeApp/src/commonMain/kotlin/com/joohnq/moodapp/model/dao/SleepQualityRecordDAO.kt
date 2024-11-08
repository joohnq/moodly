package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.model.DatabaseConstants

@Dao
interface SleepQualityRecordDAO {
    @Insert
    suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord)

    @Query("SELECT * FROM ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getSleepQualities(): List<SleepQualityRecord>
}