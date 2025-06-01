package com.joohnq.security.domain.use_case

import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityPreference

class UpdateSecurityUseCase(private val securityPreference: SecurityPreference) {
    suspend operator fun invoke(security: Security): Result<Boolean> =
        securityPreference.update(security)
}