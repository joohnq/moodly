package com.joohnq.domain.use_case

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.repository.UserRepository

class UpdateSoughtHelpUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(soughtHelp: ProfessionalHelp): Result<Boolean> =
        userRepository.updateSoughtHelp(soughtHelp)
}