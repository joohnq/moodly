package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository

class AddStressLevelUseCase(
    private val repository: StressLevelRepository,
) {
    suspend operator fun invoke(stressLevelRecord: StressLevelRecord): Result<Boolean> = repository.add(stressLevelRecord)
}