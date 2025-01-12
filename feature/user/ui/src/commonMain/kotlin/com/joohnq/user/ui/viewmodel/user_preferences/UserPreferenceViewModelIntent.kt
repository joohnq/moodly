package com.joohnq.user.ui.viewmodel.user_preferences

sealed class UserPreferenceViewModelIntent {
    data object AddUserPreferences : UserPreferenceViewModelIntent()
    data object GetUserPreferences : UserPreferenceViewModelIntent()
    data object LogoutUserPreferences : UserPreferenceViewModelIntent()
    data object ResetUpdating : UserPreferenceViewModelIntent()

    data class UpdateSkipWelcome(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()

    data class UpdateSkipOnboarding(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()

    data class UpdateSkipAuth(
        val value: Boolean = true,
    ) : UserPreferenceViewModelIntent()
}
