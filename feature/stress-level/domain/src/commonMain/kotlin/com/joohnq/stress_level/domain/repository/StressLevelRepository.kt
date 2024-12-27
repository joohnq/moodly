package com.joohnq.stress_level.domain.repository

import com.joohnq.stress_level.domain.entity.StressLevelRecord

interface StressLevelRepository {
    suspend fun getStressLevels(): List<StressLevelRecord>
    suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Boolean
}