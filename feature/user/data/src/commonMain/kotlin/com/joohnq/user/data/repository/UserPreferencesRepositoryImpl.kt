package com.joohnq.user.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.data_source.UserPreferencesDataSource
import com.joohnq.domain.repository.UserPreferencesRepository

class UserPreferencesRepositoryImpl(private val dataSource: UserPreferencesDataSource) :
    UserPreferencesRepository {
    override suspend fun getUserPreferences(): UserPreferences? =
        dataSource.getUserPreferences()

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Boolean =
        executeTryCatchPrinting {
            dataSource.addUserPreferences(userPreferences)
        }

    override suspend fun insertUserPreferences(): Boolean =
        executeTryCatchPrinting {
            dataSource.addUserPreferences(UserPreferences())
        }

    override suspend fun updateSkipWelcomeScreen(value: Boolean): Boolean =
        executeTryCatchPrinting {
            dataSource.updateSkipWelcomeScreen(value)
        }

    override suspend fun updateSkipOnboardingScreen(value: Boolean): Boolean =
        executeTryCatchPrinting {
            dataSource.updateSkipOnboardingScreen(value)
        }

    override suspend fun updateSkipGetUserNameScreen(value: Boolean): Boolean =
        executeTryCatchPrinting {
            dataSource.updateSkipGetUserNameScreen(value)
        }
}