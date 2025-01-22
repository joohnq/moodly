package com.joohnq.user.ui.viewmodel.user_preferences

sealed interface UserPreferenceIntent {
    data object AddUserPreferences : UserPreferenceIntent
    data object GetUserPreferences : UserPreferenceIntent
    data object ResetUpdating : UserPreferenceIntent
    data class UpdateSkipWelcome(
        val value: Boolean = true,
    ) : UserPreferenceIntent

    data class UpdateSkipOnboarding(
        val value: Boolean = true,
    ) : UserPreferenceIntent

    data class UpdateSkipAuth(
        val value: Boolean = true,
    ) : UserPreferenceIntent

    data class UpdateSkipSecurity(
        val value: Boolean = true,
    ) : UserPreferenceIntent
}
