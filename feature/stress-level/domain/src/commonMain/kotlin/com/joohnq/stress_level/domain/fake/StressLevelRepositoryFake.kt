package com.joohnq.stress_level.domain.fake

import com.joohnq.core.test.CustomFake
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import kotlinx.datetime.LocalDateTime

class StressLevelRepositoryFake : StressLevelRepository, CustomFake {
    override var shouldThrowError: Boolean = false
    private val items = mutableListOf(
        StressLevelRecord(
            id = 1,
            stressLevel = StressLevel.One,
            stressors = listOf(),
            date = LocalDateTime(2025, 1, 1, 0, 0, 0)
        ),
        StressLevelRecord(
            id = 2,
            stressLevel = StressLevel.Two,
            stressors = listOf(
                Stressor.Work
            ),
            date = LocalDateTime(2025, 1, 1, 0, 0, 0)
        )
    )

    override suspend fun getStressLevels(): Result<List<StressLevelRecord>> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get stress levels"))

        return Result.success(items)
    }

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add stress level"))

        items.add(stressLevelRecord)

        return Result.success(true)
    }
}