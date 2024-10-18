package com.joohnq.moodapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_PREFERENCES_DATABASE)
@Serializable
data class UserPreferences(
    @PrimaryKey val id: String = "1",
    @ColumnInfo(name = DatabaseConstants.SKIP_WELCOME_SCREEN) val skipWelcomeScreen: Boolean = false,
    @ColumnInfo(name = DatabaseConstants.SKIP_ONBOARDING_SCREEN) val skipOnboardingScreen: Boolean = false,
    @ColumnInfo(name =DatabaseConstants.SKIP_GET_USER_NAME_SCREEN) val skipGetUserNameScreen: Boolean = false
)