package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository

class GetAllStressLevelUseCase(
    private val repository: StressLevelRepository,
) {
    suspend operator fun invoke(): Result<List<StressLevelRecord>> = repository.records.value
}