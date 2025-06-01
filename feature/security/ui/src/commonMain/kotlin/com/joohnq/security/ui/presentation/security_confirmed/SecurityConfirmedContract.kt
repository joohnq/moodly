package com.joohnq.security.ui.presentation.security_confirmed

sealed interface SecurityConfirmedContract {
    sealed interface Event : SecurityConfirmedContract {
        data object OnContinue : Event
    }
}