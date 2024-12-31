package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository
import org.koin.core.annotation.Factory

@Factory
class InsertUserPreferencesUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(): Boolean = userPreferencesRepository.insertUserPreferences()
}