package com.joohnq.domain.repository

import com.joohnq.domain.entity.UserPreferences

interface UserPreferencesRepository {
    suspend fun getUserPreferences(): Result<UserPreferences>
    suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean>
    suspend fun insertUserPreferences(): Result<Boolean>
    suspend fun updateSkipWelcome(value: Boolean): Result<Boolean>
    suspend fun updateSkipOnboarding(value: Boolean): Result<Boolean>
    suspend fun updateSkipAuth(value: Boolean): Result<Boolean>
    suspend fun updateSkipSecurity(value: Boolean): Result<Boolean>
}
