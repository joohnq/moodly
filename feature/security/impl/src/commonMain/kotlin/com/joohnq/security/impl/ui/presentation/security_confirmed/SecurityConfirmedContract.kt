package com.joohnq.security.impl.ui.presentation.security_confirmed

sealed interface SecurityConfirmedContract {
    sealed interface Event {
        data object OnContinue : Event
    }
}
