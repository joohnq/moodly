package com.joohnq.preferences.domain.use_case

import com.joohnq.preferences.domain.entity.AppPreferences
import com.joohnq.preferences.domain.repository.PreferencesRepository

class GetUserPreferencesUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(): Result<AppPreferences> =
        repository.getUserPreferences()
}