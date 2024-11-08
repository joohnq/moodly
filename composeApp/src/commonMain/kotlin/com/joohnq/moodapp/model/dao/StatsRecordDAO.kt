package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.entities.StatsRecord

@Dao
interface StatsRecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStats(statsRecord: StatsRecord)

    @Query("SELECT * FROM ${DatabaseConstants.STATS_RECORD_DATABASE} ORDER BY date ASC")
    suspend fun getStats(): List<StatsRecord>
}