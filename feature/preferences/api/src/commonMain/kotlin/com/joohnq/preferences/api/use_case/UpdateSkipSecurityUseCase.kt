package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipSecurityUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(value: Boolean): Result<Unit> =
        try {
            repository.updateSkipSecurity(value)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
