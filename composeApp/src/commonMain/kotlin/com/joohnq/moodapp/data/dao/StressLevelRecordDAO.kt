package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.domain.StressLevelRecord

@Dao
interface StressLevelRecordDAO {
    @Insert
    suspend fun addStressLevel(
        stressLevelRecord: StressLevelRecord
    )

    @Query("SELECT * FROM ${DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStressLevels(): List<StressLevelRecord>
}