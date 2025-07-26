package com.joohnq.stress_level.api.use_case

import com.joohnq.stress_level.api.repository.StressLevelRepository

class DeleteStressLevelUseCase(
    private val repository: StressLevelRepository,
) {
    suspend operator fun invoke(id: Int): Result<Boolean> = repository.delete(id)
}
