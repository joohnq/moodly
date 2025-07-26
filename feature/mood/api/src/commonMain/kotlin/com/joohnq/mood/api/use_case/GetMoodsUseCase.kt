package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository

class GetMoodsUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(): Result<List<MoodRecord>> = moodRepository.getMoodRecords()
}
