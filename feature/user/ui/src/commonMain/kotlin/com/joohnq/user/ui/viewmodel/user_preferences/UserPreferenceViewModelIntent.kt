package com.joohnq.user.ui.viewmodel.user_preferences

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

    data class UpdateSkipUserNameScreen(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()
}
