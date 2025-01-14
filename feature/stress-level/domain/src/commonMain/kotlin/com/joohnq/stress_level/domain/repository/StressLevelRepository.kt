package com.joohnq.stress_level.domain.repository

import com.joohnq.stress_level.domain.entity.StressLevelRecord
import kotlinx.datetime.LocalDate

interface StressLevelRepository {
    suspend fun getStressLevels(): Result<List<StressLevelRecord>>
    suspend fun getStressLevelByDate(date: LocalDate): Result<StressLevelRecord?>
    suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Result<Boolean>
}