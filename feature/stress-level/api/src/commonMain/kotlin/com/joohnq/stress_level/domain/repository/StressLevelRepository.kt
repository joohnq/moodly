package com.joohnq.stress_level.domain.repository

import com.joohnq.stress_level.domain.entity.StressLevelRecord

interface StressLevelRepository {
    suspend fun getRecords(): Result<List<StressLevelRecord>>
    suspend fun addRecord(stressLevelRecord: StressLevelRecord): Result<Boolean>
    suspend fun delete(id: Int): Result<Boolean>
}