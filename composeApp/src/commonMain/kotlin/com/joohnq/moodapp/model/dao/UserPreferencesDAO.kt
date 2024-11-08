package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.entities.UserPreferences

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM ${DatabaseConstants.USER_PREFERENCES_DATABASE} WHERE id = 1")
    suspend fun getUserPreferences(): UserPreferences

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserPreferences(
        userPreferences: UserPreferences = UserPreferences.init()
    )

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_WELCOME_SCREEN} = :value WHERE id = 1")
    suspend fun updateSkipWelcomeScreen(value: Boolean)

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_ONBOARDING_SCREEN} = :value WHERE id = 1")
    suspend fun updateSkipOnboardingScreen(value: Boolean)

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_GET_USER_NAME_SCREEN} = :value WHERE id = 1")
    suspend fun updateSkipGetUserNameScreen(value: Boolean)
}