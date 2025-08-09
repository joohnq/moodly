package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipWelcomeUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(value: Boolean): Result<Unit> =
        try {
            repository.updateSkipWelcome(value)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
