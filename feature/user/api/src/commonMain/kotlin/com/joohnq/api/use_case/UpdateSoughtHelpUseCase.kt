package com.joohnq.api.use_case

import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.repository.UserRepository

class UpdateSoughtHelpUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(soughtHelp: ProfessionalHelp): Result<Boolean> = userRepository.updateSoughtHelp(soughtHelp)
}
