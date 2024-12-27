package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.repository.UserRepository

class AddUserPreferencesUseCase(private val repository: UserPreferencesRepository) {
    suspend operator fun invoke(userPreferences: UserPreferences): Boolean = repository.addUserPreferences(userPreferences)
}