package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.repository.MoodRepository

class DeleteStatsUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> =
        moodRepository.deleteMoodRecord(id)
}