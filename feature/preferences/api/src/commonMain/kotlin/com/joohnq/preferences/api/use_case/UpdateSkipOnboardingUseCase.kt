package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipOnboardingUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> =
        repository.updateSkipOnboarding(value)
}