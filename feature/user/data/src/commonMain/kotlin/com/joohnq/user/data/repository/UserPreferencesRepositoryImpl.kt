package com.joohnq.user.data.repository

import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesDataSource
import com.joohnq.domain.repository.UserPreferencesRepository


class UserPreferencesRepositoryImpl(private val dataSource: UserPreferencesDataSource) :
    UserPreferencesRepository {
    override suspend fun getUserPreferences(): UserPreferences? =
        dataSource.getUserPreferences()

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean =
        try {
            dataSource.addUserPreferences(userPreferences)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun insertUserPreferences(): Boolean =
        try {
            dataSource.addUserPreferences(UserPreferences())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean =
        try {
            dataSource.updateSkipWelcomeScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean =
        try {
            dataSource.updateSkipOnboardingScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean =
        try {
            dataSource.updateSkipGetUserNameScreen(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}