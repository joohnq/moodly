package com.joohnq.security.domain.use_case

import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityPreference

class GetSecurityUseCase(private val securityPreference: SecurityPreference) {
    suspend operator fun invoke(): Result<Security> = securityPreference.get()
}