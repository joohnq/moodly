package com.joohnq.user.data.data_source

import com.joohnq.core.database.converters.BooleanConverter
import com.joohnq.domain.data_source.UserPreferencesDataSource
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.user.database.UserDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserPreferencesDataSourceImpl(private val database: UserDatabaseSql) :
    UserPreferencesDataSource {
    private val query = database.userPreferencesQueries
    override suspend fun getUserPreferences(): UserPreferences? =
        withContext(Dispatchers.IO) {
            query.getUserPreferences { id, skipWelcomeScreen, skipOnboardingScreen, skipUserNameScreen ->
                UserPreferences(
                    id = id.toInt(),
                    skipWelcomeScreen = BooleanConverter.toValue(skipWelcomeScreen),
                    skipOnboardingScreen = BooleanConverter.toValue(skipOnboardingScreen),
                    skipUserNameScreen = BooleanConverter.toValue(skipUserNameScreen)
                )
            }.executeAsOneOrNull()
        }

    override suspend fun addUserPreferences(userPreferences: UserPreferences) =
        withContext(Dispatchers.IO) {
            query.addUserPreferences(
                skipWelcomeScreen = BooleanConverter.fromValue(userPreferences.skipWelcomeScreen),
                skipOnboardingScreen = BooleanConverter.fromValue(userPreferences.skipOnboardingScreen),
                skipUserNameScreen = BooleanConverter.fromValue(userPreferences.skipUserNameScreen)
            )
        }

    override suspend fun insertUserPreferences() =
        withContext(Dispatchers.IO) {
            query.insertUserPreferences()
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean) =
        withContext(Dispatchers.IO) {
            query.updateSkipWelcomeScreen(BooleanConverter.fromValue(value))
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean) =
        withContext(Dispatchers.IO) {
            query.updateSkipOnboardingScreen(BooleanConverter.fromValue(value))
        }

    override suspend fun updateSkipUserNameScreen(value: Boolean) =
        withContext(Dispatchers.IO) {
            query.updateSkipUserNameScreen(BooleanConverter.fromValue(value))
        }
}