package com.joohnq.stress_level.domain.use_case

import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import org.koin.core.annotation.Factory

@Factory
class GetStressLevelsUseCase(private val stressLevelRepository: StressLevelRepository) {
    suspend operator fun invoke(): List<StressLevelRecord> = stressLevelRepository.getStressLevels()
}
