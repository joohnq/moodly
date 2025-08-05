package com.joohnq.api.use_case

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.repository.UserRepository

class UpdateMedicationsSupplementsUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        repository.updateMedicationsSupplements(medicationsSupplements)
}