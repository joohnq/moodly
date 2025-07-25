package com.joohnq.preferences.impl.ui.viewmodel

import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface PreferencesContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object GetPreferences : Intent
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

    sealed interface SideEffect {
        data class ShowError(val message: String) : SideEffect
        data object UpdatedPreferences : SideEffect
    }

    data class State(
        val userPreferences: UiState<AppPreferences> = UiState.Idle,
    )
}