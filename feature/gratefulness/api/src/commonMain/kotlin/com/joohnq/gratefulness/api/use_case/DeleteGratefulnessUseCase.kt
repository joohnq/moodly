package com.joohnq.gratefulness.api.use_case

import com.joohnq.gratefulness.api.repository.GratefulnessRepository

class DeleteGratefulnessUseCase(
    private val repository: GratefulnessRepository,
) {
    suspend operator fun invoke(id: Int): Result<Unit> =
        try {
            repository.delete(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
