package com.joohnq.api.use_case

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.repository.UserRepository

class UpdateMedicationsSupplementsUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Result<Unit> =
        try {
            repository.updateMedicationsSupplements(medicationsSupplements)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
