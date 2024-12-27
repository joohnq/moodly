package com.joohnq.stress_level.domain.use_case

import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository

class AddStressLevelUseCase(private val repository: StressLevelRepository) {
    suspend operator fun invoke(stressLevelRecord: StressLevelRecord): Boolean =
        repository.addStressLevel(stressLevelRecord)
}
