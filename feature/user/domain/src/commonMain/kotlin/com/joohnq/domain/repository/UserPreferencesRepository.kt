package com.joohnq.domain.repository

import com.joohnq.domain.entity.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): Result<UserPreferences>
    suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean>
    suspend fun insertUserPreferences(): Result<Boolean>
    suspend fun updateSkipWelcomeScreen(value: Boolean): Result<Boolean>
    suspend fun updateSkipOnboardingScreen(value: Boolean): Result<Boolean>
    suspend fun updateSkipGetUserNameScreen(value: Boolean): Result<Boolean>
}
