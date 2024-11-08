package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.entities.StressLevelRecord

@Dao
interface StressLevelRecordDAO {
    @Insert
    suspend fun addStressLevel(stressLevelRecord: StressLevelRecord)

    @Query("SELECT * FROM ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStressLevels(): List<StressLevelRecord>
}