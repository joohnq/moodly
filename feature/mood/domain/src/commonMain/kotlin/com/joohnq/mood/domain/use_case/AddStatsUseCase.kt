package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository

class AddStatsUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(record: MoodRecord): Result<Boolean> =
        moodRepository.addMoodRecord(record)
}