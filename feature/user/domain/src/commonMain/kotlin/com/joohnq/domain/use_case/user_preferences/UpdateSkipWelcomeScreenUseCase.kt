package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository


class UpdateSkipWelcomeScreenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(updateSkipWelcomeScreen: Boolean): Boolean =
        userPreferencesRepository.updateSkipWelcomeScreen(updateSkipWelcomeScreen)
}