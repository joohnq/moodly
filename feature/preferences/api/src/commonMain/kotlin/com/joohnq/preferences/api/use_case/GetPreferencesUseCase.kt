package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.preferences.api.repository.PreferencesRepository

class GetPreferencesUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(): Result<AppPreferences> =
        try {
            val preferences = repository.get() ?: error("Preferences is null")

            Result.success(preferences)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
