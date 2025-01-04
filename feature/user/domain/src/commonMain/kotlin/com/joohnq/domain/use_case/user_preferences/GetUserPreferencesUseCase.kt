package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository

class GetUserPreferencesUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Result<UserPreferences> =
        userPreferencesRepository.getUserPreferences()
}