package com.joohnq.security.impl.ui.presentation.security_confirmed

import com.joohnq.ui.UnidirectionalViewModelWithoutState

sealed interface SecurityConfirmedContract {
    interface ViewModel : UnidirectionalViewModelWithoutState<Intent, SideEffect>

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    sealed interface Intent {
        data object Action : Intent
    }
}
