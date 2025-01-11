package com.joohnq.user.data.repository

import com.joohnq.core.database.converters.BooleanConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.user.database.UserDatabaseSql

class UserPreferencesRepositoryImpl(private val database: UserDatabaseSql) :
    UserPreferencesRepository {
    private val query = database.userPreferencesQueries
    override suspend fun getUserPreferences(): Result<UserPreferences> =
        executeTryCatchResult {
            query.getUserPreferences { id, skipWelcomeScreen, skipOnboardingScreen, skipUserNameScreen ->
                UserPreferences(
                    id = id.toInt(),
                    skipWelcomeScreen = BooleanConverter.toValue(skipWelcomeScreen),
                    skipOnboardingScreen = BooleanConverter.toValue(skipOnboardingScreen),
                    skipUserNameScreen = BooleanConverter.toValue(skipUserNameScreen)
                )
            }.executeAsOneOrNull() ?: throw Exception("User preferences not found")
        }

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean> =
        executeTryCatchResult {
            query.addUserPreferences(
                skipWelcomeScreen = BooleanConverter.fromValue(userPreferences.skipWelcomeScreen),
                skipOnboardingScreen = BooleanConverter.fromValue(userPreferences.skipOnboardingScreen),
                skipUserNameScreen = BooleanConverter.fromValue(userPreferences.skipUserNameScreen)
            )
            true
        }

    override suspend fun insertUserPreferences(): Result<Boolean> =
        executeTryCatchResult {
            query.insertUserPreferences()
            true
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipWelcomeScreen(BooleanConverter.fromValue(value))
            true
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipOnboardingScreen(BooleanConverter.fromValue(value))
            true
        }

    override suspend fun updateSkipUserNameScreen(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipUserNameScreen(BooleanConverter.fromValue(value))
            true
        }
}