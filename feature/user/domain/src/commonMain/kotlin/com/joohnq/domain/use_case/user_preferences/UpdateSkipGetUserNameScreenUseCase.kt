package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipGetUserNameScreenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(updateSkipGetUserNameScreen: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipGetUserNameScreen(updateSkipGetUserNameScreen)
}