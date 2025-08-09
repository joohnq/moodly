package com.joohnq.preferences.api.repository

import com.joohnq.preferences.api.entity.AppPreferences

interface PreferencesRepository {
    suspend fun getUserPreferences(): AppPreferences?

    suspend fun updateSkipWelcome(value: Boolean)

    suspend fun updateSkipOnboarding(value: Boolean)

    suspend fun updateSkipAuth(value: Boolean)

    suspend fun updateSkipSecurity(value: Boolean)
}
