package com.joohnq.preferences.api.repository

import com.joohnq.preferences.api.entity.AppPreferences

interface PreferencesRepository {
    suspend fun getUserPreferences(): Result<AppPreferences>
    suspend fun updateSkipWelcome(value: Boolean): Result<Boolean>
    suspend fun updateSkipOnboarding(value: Boolean): Result<Boolean>
    suspend fun updateSkipAuth(value: Boolean): Result<Boolean>
    suspend fun updateSkipSecurity(value: Boolean): Result<Boolean>
}
