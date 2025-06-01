package com.joohnq.preferences.ui.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.preferences.domain.entity.AppPreferences

sealed interface PreferencesContract {
    sealed interface Intent : PreferencesContract {
        data object Get : Intent
        data class UpdateSkipWelcome(
            val value: Boolean = true,
        ) : Intent

        data class UpdateSkipOnboarding(
            val value: Boolean = true,
        ) : Intent

        data class UpdateSkipAuth(
            val value: Boolean = true,
        ) : Intent

        data class UpdateSkipSecurity(
            val value: Boolean = true,
        ) : Intent
    }

    sealed interface SideEffect : PreferencesContract {
        data class ShowError(val error: Throwable) : SideEffect
        data object PreferencesUpdated : SideEffect
    }

    data class State(
        val status: UiState<AppPreferences> = UiState.Idle,
    ) : PreferencesContract
}