package com.joohnq.welcome.impl.ui.presentation.welcome

import com.joohnq.ui.UnidirectionalViewModelWithoutState

sealed interface WelcomeContract {
    interface ViewModel : UnidirectionalViewModelWithoutState<Intent, SideEffect>

    sealed interface Event {
        data object OnNext : Event
    }

    sealed interface Intent {
        data object UpdateSkipWelcome : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }
}
