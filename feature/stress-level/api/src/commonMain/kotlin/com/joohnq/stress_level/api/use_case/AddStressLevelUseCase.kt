package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository

class AddStressLevelUseCase(
    private val repository: StressLevelRepository,
) {
    suspend operator fun invoke(record: StressLevelRecord): Result<Unit> =
        try {
            repository.add(record)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
