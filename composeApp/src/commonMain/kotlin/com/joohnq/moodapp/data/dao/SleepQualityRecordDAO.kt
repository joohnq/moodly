package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.data.DatabaseConstants
import kotlinx.datetime.LocalDateTime

@Dao
interface SleepQualityRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} (${DatabaseConstants.SLEEP_QUALITY}, ${DatabaseConstants.START_SLEEPING}, ${DatabaseConstants.END_SLEEPING},${DatabaseConstants.SLEEP_INFLUENCES}, date) VALUES (:sleepQuality, :startSleeping, :endSleeping, :sleepInfluences, :date)")
    suspend fun addSleepQuality(
        sleepQuality: SleepQuality,
        startSleeping: String,
        endSleeping: String,
        sleepInfluences: List<SleepInfluences>,
        date: LocalDateTime = DatetimeManager.getCurrentDateTime()
    )

    @Query("SELECT * FROM ${DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getSleepQualities(): List<SleepQualityRecord>
}