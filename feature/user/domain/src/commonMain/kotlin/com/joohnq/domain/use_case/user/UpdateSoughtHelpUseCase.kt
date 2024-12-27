package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class UpdateSoughtHelpUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(soughtHelp: Boolean): Boolean =
        repository.updateSoughtHelp(soughtHelp)
}