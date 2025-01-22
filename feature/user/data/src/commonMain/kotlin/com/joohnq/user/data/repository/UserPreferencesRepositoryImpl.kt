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
            query.getUserPreferences { id, skipWelcome, skipOnboarding, skipAuth, skipSecurity ->
                UserPreferences(
                    id = id.toInt(),
                    skipWelcome = BooleanConverter.toValue(skipWelcome),
                    skipOnboarding = BooleanConverter.toValue(skipOnboarding),
                    skipAuth = BooleanConverter.toValue(skipAuth),
                    skipSecurity = BooleanConverter.toValue(skipSecurity),
                )
            }.executeAsOneOrNull() ?: throw Exception("User preferences not found")
        }

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean> =
        executeTryCatchResult {
            query.addUserPreferences(
                skipWelcome = BooleanConverter.fromValue(userPreferences.skipWelcome),
                skipOnboarding = BooleanConverter.fromValue(userPreferences.skipOnboarding),
                skipAuth = BooleanConverter.fromValue(userPreferences.skipAuth),
                skipSecurity = BooleanConverter.fromValue(userPreferences.skipSecurity),
            )
            true
        }

    override suspend fun insertUserPreferences(): Result<Boolean> =
        executeTryCatchResult {
            query.insertUserPreferences()
            true
        }

    override suspend fun updateSkipWelcome(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipWelcome(BooleanConverter.fromValue(value))
            true
        }

    override suspend fun updateSkipOnboarding(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipOnboarding(BooleanConverter.fromValue(value))
            true
        }

    override suspend fun updateSkipAuth(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipAuth(BooleanConverter.fromValue(value))
            true
        }

    override suspend fun updateSkipSecurity(value: Boolean): Result<Boolean> =
        executeTryCatchResult {
            query.updateSkipSecurity(BooleanConverter.fromValue(value))
            true
        }
}