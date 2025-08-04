package com.joohnq.splash.impl.ui.presentation.splash

import com.joohnq.ui.UnidirectionalViewModelWithoutState

interface SplashContract {
    interface ViewModel : UnidirectionalViewModelWithoutState<Intent, SideEffect>

    sealed interface Intent {
        data object Init : Intent
    }

    sealed interface SideEffect {
        data object NavigateToUnlock : SideEffect

        data object NavigateToWelcome : SideEffect

        data object NavigateToAuth : SideEffect

        data object NavigateToDashboard : SideEffect

        data object NavigateToOnboarding : SideEffect

        data object NavigateToSecurityCorrupted : SideEffect

        data object NavigateToSecurity : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }
}
