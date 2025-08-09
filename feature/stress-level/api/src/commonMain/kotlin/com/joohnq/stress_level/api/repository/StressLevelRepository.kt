package com.joohnq.stress_level.api.repository

import com.joohnq.stress_level.api.entity.StressLevelRecord
import kotlinx.coroutines.flow.Flow

interface StressLevelRepository {
    fun observe(): Flow<List<StressLevelRecord>>

    suspend fun add(stressLevelRecord: StressLevelRecord)

    suspend fun delete(id: Int)
}
