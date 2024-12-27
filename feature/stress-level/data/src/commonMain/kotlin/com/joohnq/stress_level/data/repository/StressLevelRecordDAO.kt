package com.joohnq.stress_level.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.mood.data.DatabaseConstants
import com.joohnq.mood.domain.StressLevelRecord

@Dao
interface StressLevelRecordDAO {
    @Insert
    suspend fun addStressLevel(
        stressLevelRecord: StressLevelRecord
    )

    @Query("SELECT * FROM ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStressLevels(): List<StressLevelRecord>
}