package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.LocalDateTime

@Dao
interface StressLevelRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} (${DatabaseConstants.STRESS_LEVEL}, stressors, date) VALUES (:stressLevel, :stressors, :date)")
    suspend fun addStressLevel(
        stressLevel: StressLevel,
        stressors: String,
        date: LocalDateTime = DatetimeManager.getCurrentDateTime()
    )

    @Query("SELECT * FROM ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStressLevels(): List<StressLevelRecord>
}