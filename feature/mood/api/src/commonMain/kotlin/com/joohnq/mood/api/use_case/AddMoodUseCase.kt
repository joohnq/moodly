package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository

class AddMoodUseCase(
    private val repository: MoodRepository,
) {
    suspend operator fun invoke(record: MoodRecord): Result<Unit> =
        try {
            repository.add(record)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
