package com.joohnq.api.use_case

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.repository.UserRepository

class UpdateMedicationsSupplementsUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        userRepository.updateMedicationsSupplements(medicationsSupplements)
}