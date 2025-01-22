package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipWelcomeUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipWelcome(value)
}