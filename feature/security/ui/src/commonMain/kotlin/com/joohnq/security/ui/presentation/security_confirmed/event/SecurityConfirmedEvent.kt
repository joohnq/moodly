package com.joohnq.security.ui.presentation.security_confirmed.event

sealed class SecurityConfirmedEvent {
    data object OnContinue : SecurityConfirmedEvent()
}