package com.joohnq.domain.use_case

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.repository.UserRepository

class UpdatePhysicalSymptomsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(physicalSymptoms: PhysicalSymptoms): Result<Boolean> =
        userRepository.updatePhysicalSymptoms(physicalSymptoms)
}