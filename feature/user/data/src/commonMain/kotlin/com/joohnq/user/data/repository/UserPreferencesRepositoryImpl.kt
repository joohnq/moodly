package com.joohnq.user.data.repository

import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesDataSource
import com.joohnq.domain.repository.UserPreferencesRepository
import org.koin.core.annotation.Single

@Single(binds = [UserPreferencesRepository::class])
class UserPreferencesRepositoryImpl(private val userPreferencesDataSource: UserPreferencesDataSource) :
    UserPreferencesRepository {
    override suspend fun getUserPreferences(): UserPreferences =
        userPreferencesDataSource.getUserPreferences()

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean =
        try {
            userPreferencesDataSource.addUserPreferences(userPreferences)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun insertUserPreferences(): Boolean =
        try {
            userPreferencesDataSource.addUserPreferences()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean =
        try {
            userPreferencesDataSource.updateSkipWelcomeScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean =
        try {
            userPreferencesDataSource.updateSkipOnboardingScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean =
        try {
            userPreferencesDataSource.updateSkipGetUserNameScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}