package com.joohnq.preferences.domain.use_case

import com.joohnq.preferences.domain.repository.PreferencesRepository

class UpdateSkipOnboardingUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> =
        repository.updateSkipOnboarding(value)
}