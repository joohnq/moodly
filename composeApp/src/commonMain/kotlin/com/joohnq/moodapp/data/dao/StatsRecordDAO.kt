package com.joohnq.moodapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.domain.StatsRecord

@Dao
interface StatsRecordDAO {
    @Insert
    suspend fun addStats(statsRecord: StatsRecord)

    @Query("SELECT * FROM ${DatabaseConstants.STATS_RECORD_DATABASE} ORDER BY date DESC")
    suspend fun getStats(): List<StatsRecord>

    @Query("DELETE FROM ${DatabaseConstants.STATS_RECORD_DATABASE} WHERE id = :id")
    suspend fun deleteStat(id: Int)
}