package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joohnq.moodapp.view.entities.MoodDb
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMood(moodDb: MoodDb)

    @Query("SELECT * FROM moods")
    fun getMoods(): Flow<List<MoodDb>>
}