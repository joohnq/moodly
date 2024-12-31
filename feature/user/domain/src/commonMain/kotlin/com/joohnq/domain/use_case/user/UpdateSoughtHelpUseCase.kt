package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.repository.UserRepository
import org.koin.core.annotation.Factory

@Factory
class UpdateSoughtHelpUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(soughtHelp: ProfessionalHelp): Boolean =
        userRepository.updateSoughtHelp(soughtHelp)
}