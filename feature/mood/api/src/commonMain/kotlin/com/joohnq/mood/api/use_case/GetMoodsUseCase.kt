package com.joohnq.mood.api.use_case

import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository
import kotlinx.coroutines.flow.Flow

class GetMoodsUseCase(
    private val repository: MoodRepository,
) {
    operator fun invoke(): Flow<List<MoodRecord>> = repository.observe()
}
