package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.repository.SleepQualityRepository

class DeleteSleepQualityUseCase(
    private val repository: SleepQualityRepository,
) {
    suspend operator fun invoke(id: Int): Result<Unit> =
        try {
            repository.delete(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
