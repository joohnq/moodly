package com.joohnq.domain.use_case.user_preferences

import com.joohnq.domain.repository.UserPreferencesRepository

class UpdateSkipSecurityUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> =
        userPreferencesRepository.updateSkipSecurity(value)
}