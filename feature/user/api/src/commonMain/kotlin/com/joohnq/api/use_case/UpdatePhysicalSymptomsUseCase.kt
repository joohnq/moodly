package com.joohnq.api.use_case

import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.repository.UserRepository

class UpdatePhysicalSymptomsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(physicalSymptoms: PhysicalSymptoms): Result<Boolean> =
        userRepository.updatePhysicalSymptoms(physicalSymptoms)
}