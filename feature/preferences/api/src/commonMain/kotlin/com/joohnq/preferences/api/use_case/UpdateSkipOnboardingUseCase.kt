package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipOnboardingUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(value: Boolean): Result<Unit> =
        try {
            repository.updateSkipOnboarding(value)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
