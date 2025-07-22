package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.preferences.api.repository.PreferencesRepository

class GetUserPreferencesUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(): Result<AppPreferences> =
        repository.getUserPreferences()
}