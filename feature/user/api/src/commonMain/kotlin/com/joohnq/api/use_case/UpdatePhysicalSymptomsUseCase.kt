package com.joohnq.api.use_case

import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.repository.UserRepository

class UpdatePhysicalSymptomsUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(physicalSymptoms: PhysicalSymptoms): Result<Unit> =
        try {
            repository.updatePhysicalSymptoms(physicalSymptoms)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
