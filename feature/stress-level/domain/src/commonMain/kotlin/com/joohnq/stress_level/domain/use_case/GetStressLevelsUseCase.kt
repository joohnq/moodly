package com.joohnq.stress_level.domain.use_case

import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository

class GetStressLevelsUseCase(private val repository: StressLevelRepository) {
    suspend operator fun invoke(): List<StressLevelRecord> = repository.getStressLevels()
}
