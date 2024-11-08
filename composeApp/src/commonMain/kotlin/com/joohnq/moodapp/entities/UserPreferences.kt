package com.joohnq.moodapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_PREFERENCES_DATABASE)
@Serializable
data class UserPreferences(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = DatabaseConstants.SKIP_WELCOME_SCREEN) val skipWelcomeScreen: Boolean,
    @ColumnInfo(name = DatabaseConstants.SKIP_ONBOARDING_SCREEN) val skipOnboardingScreen: Boolean,
    @ColumnInfo(name = DatabaseConstants.SKIP_GET_USER_NAME_SCREEN) val skipGetUserNameScreen: Boolean
) {
    companion object {
        fun init(): UserPreferences = UserPreferences(
            id = 1,
            skipWelcomeScreen = false,
            skipOnboardingScreen = false,
            skipGetUserNameScreen = false
        )
    }
}
