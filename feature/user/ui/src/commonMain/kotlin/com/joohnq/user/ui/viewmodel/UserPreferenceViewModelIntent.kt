package com.joohnq.user.ui.viewmodel

sealed class UserPreferenceViewModelIntent {
    data object AddUserPreferences : UserPreferenceViewModelIntent()
    data object GetUserPreferences : UserPreferenceViewModelIntent()
    data object LogoutUserPreferences : UserPreferenceViewModelIntent()
    data object ResetUpdating : UserPreferenceViewModelIntent()

    data class UpdateSkipWelcomeScreen(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()

    data class UpdateSkipOnboardingScreen(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()

    data class UpdateSkipGetUserNameScreen(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()
}
