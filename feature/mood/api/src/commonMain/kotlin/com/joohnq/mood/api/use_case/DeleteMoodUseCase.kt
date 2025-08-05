package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.repository.MoodRepository

class DeleteMoodUseCase(
    private val repository: MoodRepository,
) {
    suspend operator fun invoke(id: Int): Result<Boolean> = repository.delete(id)
}