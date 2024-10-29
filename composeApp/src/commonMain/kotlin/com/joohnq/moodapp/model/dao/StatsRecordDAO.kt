package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.model.entities.StatsRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMood(statsRecord: StatsRecord)

    @Query("SELECT * FROM ${DatabaseConstants.STATS_RECORD_DATABASE}")
    fun getMoods(): Flow<List<StatsRecord>>

    @Query("SELECT * FROM ${DatabaseConstants.STATS_RECORD_DATABASE} ORDER BY date DESC LIMIT 30")
    suspend fun getMonthlyMoods(): List<StatsRecord>
}