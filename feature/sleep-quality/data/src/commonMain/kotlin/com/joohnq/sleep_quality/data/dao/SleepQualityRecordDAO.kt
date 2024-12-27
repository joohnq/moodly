package com.joohnq.sleep_quality.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.sleep_quality.domain.constant.DatabaseConstants
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

@Dao
interface SleepQualityRecordDAO {
    @Insert
    suspend fun addSleepQuality(
        sleepQualityRecord: SleepQualityRecord
    )

    @Query("SELECT * FROM ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getSleepQualities(): List<SleepQualityRecord>
}