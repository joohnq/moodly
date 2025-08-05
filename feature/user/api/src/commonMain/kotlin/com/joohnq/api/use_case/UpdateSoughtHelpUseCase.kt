package com.joohnq.api.use_case

import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.repository.UserRepository

class UpdateSoughtHelpUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(soughtHelp: ProfessionalHelp): Result<Boolean> = repository.updateSoughtHelp(soughtHelp)
}
