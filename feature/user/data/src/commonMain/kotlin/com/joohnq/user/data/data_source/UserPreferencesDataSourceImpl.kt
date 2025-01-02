package com.joohnq.user.data.data_source

import com.joohnq.core.database.converters.BooleanConverter
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesDataSource
import com.joohnq.user.database.UserDatabaseSql


class UserPreferencesDataSourceImpl(private val database: UserDatabaseSql) :
    UserPreferencesDataSource {
    private val query = database.userPreferencesQueries
    override suspend fun getUserPreferences(): UserPreferences? {
        val res = query.getUserPreferences().executeAsOneOrNull() ?: return null
        return UserPreferences(
            id = res.id.toInt(),
            skipWelcomeScreen = BooleanConverter.toValue(res.skipWelcomeScreen),
            skipOnboardingScreen = BooleanConverter.toValue(res.skipOnboardingScreen),
            skipGetUserNameScreen = BooleanConverter.toValue(res.skipGetUserNameScreen)
        )
    }

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean =
        try {
            query.addUserPreferences(
                skipWelcomeScreen = BooleanConverter.fromValue(userPreferences.skipWelcomeScreen),
                skipOnboardingScreen = BooleanConverter.fromValue(userPreferences.skipOnboardingScreen),
                skipGetUserNameScreen = BooleanConverter.fromValue(userPreferences.skipGetUserNameScreen)
            )
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun insertUserPreferences(): Boolean =
        try {
            query.insertUserPreferences()
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean =
        try {
            query.updateSkipWelcomeScreen(BooleanConverter.fromValue(value))
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean =
        try {
            query.updateSkipOnboardingScreen(BooleanConverter.fromValue(value))
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean =
        try {
            query.updateSkipGetUserNameScreen(BooleanConverter.fromValue(value))
            true
        } catch (e: Exception) {
            false
        }
}