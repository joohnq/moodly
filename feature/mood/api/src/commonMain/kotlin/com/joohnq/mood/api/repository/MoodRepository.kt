package com.joohnq.mood.api.repository

import com.joohnq.mood.api.entity.MoodRecord
import kotlinx.coroutines.flow.Flow

interface MoodRepository {
    fun observe(): Flow<List<MoodRecord>>

    suspend fun add(record: MoodRecord)

    suspend fun deleteById(id: Long)
}
