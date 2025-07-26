package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository

class AddMoodUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(record: MoodRecord): Result<Boolean> =
        moodRepository.addMoodRecord(record)
}
