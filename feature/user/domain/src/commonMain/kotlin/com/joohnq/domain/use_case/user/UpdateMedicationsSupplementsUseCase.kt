package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class UpdateMedicationsSupplementsUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(medicationsSupplements: MedicationsSupplements): Boolean =
        repository.updateMedicationsSupplements(medicationsSupplements)
}