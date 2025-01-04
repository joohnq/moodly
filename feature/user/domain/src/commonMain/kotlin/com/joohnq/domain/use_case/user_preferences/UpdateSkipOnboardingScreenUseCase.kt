package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipOnboardingScreenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(updateSkipOnboardingScreen: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipOnboardingScreen(updateSkipOnboardingScreen)
}