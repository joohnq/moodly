package com.joohnq.user.data.data_source

import com.joohnq.core.database.converters.BooleanConverter
import com.joohnq.core.database.executeTryCatch
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.data_source.UserPreferencesDataSource
import com.joohnq.user.database.UserDatabaseSql

class UserPreferencesDataSourceImpl(private val database: UserDatabaseSql) :
    UserPreferencesDataSource {
    private val query = database.userPreferencesQueries
    override suspend fun getUserPreferences(): UserPreferences? =
        query.getUserPreferences { id, skipWelcomeScreen, skipOnboardingScreen, skipGetUserNameScreen ->
            UserPreferences(
                id = id.toInt(),
                skipWelcomeScreen = BooleanConverter.toValue(skipWelcomeScreen),
                skipOnboardingScreen = BooleanConverter.toValue(skipOnboardingScreen),
                skipGetUserNameScreen = BooleanConverter.toValue(skipGetUserNameScreen)
            )
        }.executeAsOneOrNull()

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean =
        executeTryCatch {
            query.addUserPreferences(
                skipWelcomeScreen = BooleanConverter.fromValue(userPreferences.skipWelcomeScreen),
                skipOnboardingScreen = BooleanConverter.fromValue(userPreferences.skipOnboardingScreen),
                skipGetUserNameScreen = BooleanConverter.fromValue(userPreferences.skipGetUserNameScreen)
            )
        }

    override suspend fun insertUserPreferences(): Boolean =
        executeTryCatch {
            query.insertUserPreferences()
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean =
        executeTryCatch {
            query.updateSkipWelcomeScreen(BooleanConverter.fromValue(value))
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean =
        executeTryCatch {
            query.updateSkipOnboardingScreen(BooleanConverter.fromValue(value))
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean =
        executeTryCatch {
            query.updateSkipGetUserNameScreen(BooleanConverter.fromValue(value))
        }
}