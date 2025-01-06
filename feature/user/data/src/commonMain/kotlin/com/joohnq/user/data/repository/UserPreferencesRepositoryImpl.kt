package com.joohnq.user.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.core.ui.toResultNull
import com.joohnq.domain.data_source.UserPreferencesDataSource
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository

class UserPreferencesRepositoryImpl(private val dataSource: UserPreferencesDataSource) :
    UserPreferencesRepository {
    override suspend fun getUserPreferences(): Result<UserPreferences> =
        dataSource.getUserPreferences().toResultNull("User preferences not found")

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addUserPreferences(userPreferences)
        }

    override suspend fun insertUserPreferences(): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addUserPreferences(UserPreferences())
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateSkipWelcomeScreen(value)
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateSkipOnboardingScreen(value)
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateSkipGetUserNameScreen(value)
        }
}