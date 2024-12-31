package com.joohnq.domain.repository

import com.joohnq.domain.entity.UserPreferences

interface UserPreferencesDataSource {
    suspend fun getUserPreferences(): UserPreferences?
    suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean
    suspend fun insertUserPreferences(): Boolean
    suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean
    suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean
    suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean
}
