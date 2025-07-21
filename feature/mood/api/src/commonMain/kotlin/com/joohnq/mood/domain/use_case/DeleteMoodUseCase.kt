package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.repository.MoodRepository

class DeleteMoodUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> =
        moodRepository.deleteMoodRecord(id)
}