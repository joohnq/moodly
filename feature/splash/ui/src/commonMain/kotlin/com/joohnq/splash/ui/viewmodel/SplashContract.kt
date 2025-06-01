package com.joohnq.splash.ui.viewmodel

import com.joohnq.auth.ui.viewmodel.AuthUserState
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.preferences.domain.entity.AppPreferences
import com.joohnq.security.domain.Security

sealed interface SplashContract {
    sealed interface Intent {
        data class Initialize(
            val auth: UiState<AuthUserState>,
            val user: UiState<User>,
            val preferences: UiState<AppPreferences>,
            val security: UiState<Security>
        ) : Intent
    }

    sealed interface SideEffect {
        data object NavigateToWelcome : SideEffect
        data object NavigateToOnboarding : SideEffect
        data object NavigateToAuth : SideEffect
        data object NavigateToSecurity : SideEffect
        data object NavigateToDashboard : SideEffect
        data object NavigateToUnlock : SideEffect
        data object NavigateToCorruptedSecurity : SideEffect
        data class ShowError(val error: String) : SideEffect
    }
}