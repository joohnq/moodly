package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.entities.UserPreferences
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM ${DatabaseConstants.USER_PREFERENCES_DATABASE} WHERE id = :id")
    fun getUserPreferences(id: String = "1"): Flow<UserPreferences>

    @Upsert
    suspend fun saveUserPreferences(userPreferences: UserPreferences)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserPreferences(
        userPreferences: UserPreferences = UserPreferences.init()
    )

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_WELCOME_SCREEN} = :skipWelcomeScreen WHERE id = :id")
    suspend fun setSkipWelcomeScreen(skipWelcomeScreen: Boolean = true, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_ONBOARDING_SCREEN} = :skipOnboardingScreen WHERE id = :id")
    suspend fun setSkipOnboardingScreen(skipOnboardingScreen: Boolean = true, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_PREFERENCES_DATABASE} SET ${DatabaseConstants.SKIP_GET_USER_NAME_SCREEN} = :skipGetUserNameScreen WHERE id = :id")
    suspend fun setSkipGetUserNameScreen(skipGetUserNameScreen: Boolean = true, id: String = "1")
}