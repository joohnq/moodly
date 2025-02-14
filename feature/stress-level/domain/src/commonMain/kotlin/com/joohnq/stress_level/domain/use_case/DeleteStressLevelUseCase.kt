package com.joohnq.stress_level.domain.use_case

import com.joohnq.stress_level.domain.repository.StressLevelRepository

class DeleteStressLevelUseCase(private val repository: StressLevelRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> = repository.delete(id)
}
