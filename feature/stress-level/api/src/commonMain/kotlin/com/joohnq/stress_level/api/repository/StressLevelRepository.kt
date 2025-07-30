package com.joohnq.stress_level.api.repository

import com.joohnq.stress_level.api.entity.StressLevelRecord
import kotlinx.coroutines.flow.StateFlow

interface StressLevelRepository {
    val records: StateFlow<Result<List<StressLevelRecord>>>

    suspend fun getAll(): Result<List<StressLevelRecord>>

    suspend fun add(stressLevelRecord: StressLevelRecord): Result<Boolean>

    suspend fun delete(id: Int): Result<Boolean>
}
