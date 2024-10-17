package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.joohnq.moodapp.view.entities.UserPreferences
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM user_preferences WHERE id = :id")
    fun getUserPreferences(id: String = "1"): Flow<UserPreferences>

    @Upsert
    suspend fun saveUserPreferences(userPreferences: UserPreferences)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserPreferences(
        userPreferences: UserPreferences = UserPreferences()
    )

    @Query("UPDATE user_preferences SET skip_welcome_screen = :skipWelcomeScreen WHERE id = :id")
    suspend fun setSkipWelcomeScreen(skipWelcomeScreen: Boolean = true, id: String = "1")

    @Query("UPDATE user_preferences SET skip_onboarding_screen = :skipOnboardingScreen WHERE id = :id")
    suspend fun setSkipOnboardingScreen(skipOnboardingScreen: Boolean = true, id: String = "1")

    @Query("UPDATE user_preferences SET skip_get_user_name_screen = :skipGetUserNameScreen WHERE id = :id")
    suspend fun setSkipGetUserNameScreen(skipGetUserNameScreen: Boolean = true, id: String = "1")
}