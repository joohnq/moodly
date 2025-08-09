package com.joohnq.gratefulness.api.use_case

import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.repository.GratefulnessRepository

class AddGratefulnessUseCase(
    private val repository: GratefulnessRepository,
) {
    suspend operator fun invoke(item: Gratefulness): Result<Unit> =
        try {
            repository.add(item)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
