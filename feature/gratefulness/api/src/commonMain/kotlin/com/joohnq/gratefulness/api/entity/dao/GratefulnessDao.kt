package com.joohnq.gratefulness.api.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joohnq.gratefulness.api.entity.dto.GratefulnessDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface GratefulnessDao {
    @Query("SELECT * FROM gratefulness")
    fun observe(): Flow<List<GratefulnessDTO>>

    @Insert
    suspend fun add(item: GratefulnessDTO)

    @Query("DELETE FROM gratefulness WHERE id = :id")
    suspend fun delete(id: Int)
}
