package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.repository.UserRepository

class UpdateMedicationsSupplementsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        userRepository.updateMedicationsSupplements(medicationsSupplements)
}