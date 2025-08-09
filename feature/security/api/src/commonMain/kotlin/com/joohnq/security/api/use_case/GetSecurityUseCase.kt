package com.joohnq.security.api.use_case

import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityPreference

class GetSecurityUseCase(
    private val securityPreference: SecurityPreference,
) {
    suspend operator fun invoke(): Result<Security> =
        try {
            val security = securityPreference.get()

            Result.success(security)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
