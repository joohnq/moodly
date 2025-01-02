package com.joohnq.stress_level.domain.data_source

import com.joohnq.stress_level.domain.entity.StressLevelRecord

interface StressLevelDataSource {
    suspend fun getStressLevels(): List<StressLevelRecord>
    suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Boolean
}