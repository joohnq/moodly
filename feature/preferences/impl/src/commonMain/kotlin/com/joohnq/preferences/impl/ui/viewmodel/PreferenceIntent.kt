package com.joohnq.preferences.impl.ui.viewmodel

sealed interface PreferenceIntent {
    data object GetPreferences : PreferenceIntent
    data class UpdateSkipWelcome(
        val value: Boolean = true,
    ) : PreferenceIntent

    data class UpdateSkipOnboarding(
        val value: Boolean = true,
    ) : PreferenceIntent

    data class UpdateSkipAuth(
        val value: Boolean = true,
    ) : PreferenceIntent

    data class UpdateSkipSecurity(
        val value: Boolean = true,
    ) : PreferenceIntent
}
