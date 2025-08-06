package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository
import kotlinx.coroutines.flow.Flow

class GetAllStressLevelUseCase(
    private val repository: StressLevelRepository,
) {
    operator fun invoke(): Flow<List<StressLevelRecord>> = repository.observe()
}
