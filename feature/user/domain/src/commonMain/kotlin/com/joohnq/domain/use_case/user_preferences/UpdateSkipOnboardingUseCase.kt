package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipOnboardingUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(updateSkipOnboarding: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipOnboarding(updateSkipOnboarding)
}