package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipUserNameScreenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(updateSkipUserNameScreen: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipUserNameScreen(updateSkipUserNameScreen)
}