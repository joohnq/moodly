package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.repository.UserRepository
import org.koin.core.annotation.Factory

@Factory
class UpdateMedicationsSupplementsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Boolean =
        userRepository.updateMedicationsSupplements(medicationsSupplements)
}