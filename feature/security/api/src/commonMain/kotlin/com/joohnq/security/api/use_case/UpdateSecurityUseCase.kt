package com.joohnq.security.api.use_case

import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityPreference

class UpdateSecurityUseCase(
    private val securityPreference: SecurityPreference,
) {
    suspend operator fun invoke(security: Security): Result<Boolean> = securityPreference.update(security)
}
