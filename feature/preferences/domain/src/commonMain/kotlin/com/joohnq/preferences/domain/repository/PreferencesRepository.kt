package com.joohnq.preferences.domain.repository

import com.joohnq.preferences.domain.entity.AppPreferences

interface PreferencesRepository {
    suspend fun getUserPreferences(): Result<AppPreferences>
    suspend fun updateSkipWelcome(value: Boolean): Result<Boolean>
    suspend fun updateSkipOnboarding(value: Boolean): Result<Boolean>
    suspend fun updateSkipAuth(value: Boolean): Result<Boolean>
    suspend fun updateSkipSecurity(value: Boolean): Result<Boolean>
}
