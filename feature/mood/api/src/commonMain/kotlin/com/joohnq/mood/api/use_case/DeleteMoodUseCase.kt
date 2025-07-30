package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.repository.MoodRepository

class DeleteMoodUseCase(
    private val moodRepository: MoodRepository,
) {
    suspend operator fun invoke(id: Int): Result<Boolean> = moodRepository.delete(id)
}
