package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipAuthUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(value: Boolean): Result<Unit> =
        try {
            repository.updateSkipAuth(value)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
