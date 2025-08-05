package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository

class GetMoodsUseCase(
    private val repository: MoodRepository,
) {
    suspend operator fun invoke(): Result<List<MoodRecord>> = repository.records.value
}
