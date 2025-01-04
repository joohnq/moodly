package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository

class AddUserPreferencesUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(userPreferences: UserPreferences): Result<Boolean> =
        userPreferencesRepository.addUserPreferences(userPreferences)
}