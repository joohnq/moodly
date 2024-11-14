package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.model.DatabaseConstants

@Dao
interface StressLevelRecordDAO {
    @Query("INSERT INTO ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} (${DatabaseConstants.STRESS_LEVEL}, stressors, date) VALUES (:stressLevel, :stressors, CURRENT_TIMESTAMP)")
    suspend fun addStressLevel(stressLevel: StressLevel, stressors: String)

    @Query("SELECT * FROM ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStressLevels(): List<StressLevelRecord>
}