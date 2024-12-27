package com.joohnq.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.domain.constant.DatabaseConstants
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_PREFERENCES_DATABASE)
@Serializable
data class UserPreferences(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = DatabaseConstants.SKIP_WELCOME_SCREEN) val skipWelcomeScreen: Boolean,
    @ColumnInfo(name = DatabaseConstants.SKIP_ONBOARDING_SCREEN) val skipOnboardingScreen: Boolean,
    @ColumnInfo(name = DatabaseConstants.SKIP_GET_USER_NAME_SCREEN)
    val skipGetUserNameScreen: Boolean
) {
    class Builder {
        private var id: Int = 1
        private var skipWelcomeScreen: Boolean = false
        private var skipOnboardingScreen: Boolean = false
        private var skipGetUserNameScreen: Boolean = false

        fun setSkipWelcomeScreen(skipWelcomeScreen: Boolean) =
            apply { this.skipWelcomeScreen = skipWelcomeScreen }

        fun setSkipOnboardingScreen(skipOnboardingScreen: Boolean) =
            apply { this.skipOnboardingScreen = skipOnboardingScreen }

        fun setSkipGetUserNameScreen(skipGetUserNameScreen: Boolean) =
            apply { this.skipGetUserNameScreen = skipGetUserNameScreen }

        fun build() = UserPreferences(
            id = id,
            skipWelcomeScreen = skipWelcomeScreen,
            skipOnboardingScreen = skipOnboardingScreen,
            skipGetUserNameScreen = skipGetUserNameScreen,
        )
    }

    companion object {
        fun init(): UserPreferences = Builder().build()
    }
}
