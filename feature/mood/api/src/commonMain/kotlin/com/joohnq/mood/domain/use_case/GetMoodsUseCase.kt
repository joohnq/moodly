package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository

class GetMoodsUseCase(private val moodRepository: MoodRepository) {
    suspend operator fun invoke(): Result<List<MoodRecord>> = moodRepository.getMoodRecords()
}