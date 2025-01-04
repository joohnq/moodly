package com.joohnq.domain.data_source

import com.joohnq.domain.entity.UserPreferences

interface UserPreferencesDataSource {
    suspend fun getUserPreferences(): UserPreferences?
    suspend fun addUserPreferences(userPreferences: UserPreferences)
    suspend fun insertUserPreferences()
    suspend fun updateSkipWelcomeScreen(value: Boolean)
    suspend fun updateSkipOnboardingScreen(value: Boolean)
    suspend fun updateSkipGetUserNameScreen(value: Boolean)
}
