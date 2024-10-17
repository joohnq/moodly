package com.joohnq.moodapp.view.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import kotlinx.serialization.Serializable

@Entity(tableName = "user_preferences")
@Serializable
data class UserPreferences(
    @PrimaryKey val id: String = "1",
    @ColumnInfo(name = "skip_welcome_screen") val skipWelcomeScreen: Boolean = false,
    @ColumnInfo(name = "skip_onboarding_screen") val skipOnboardingScreen: Boolean = false,
    @ColumnInfo(name = "skip_get_user_name_screen") val skipGetUserNameScreen: Boolean = false
)
