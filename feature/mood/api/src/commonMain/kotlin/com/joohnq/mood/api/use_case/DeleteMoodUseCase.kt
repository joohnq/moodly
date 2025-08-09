package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.repository.MoodRepository

class DeleteMoodUseCase(
    private val repository: MoodRepository,
) {
    suspend operator fun invoke(id: Int): Result<Unit> =
        try {
            repository.delete(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
