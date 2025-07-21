package com.joohnq.security.ui.presentation.security_confirmed.event

sealed interface SecurityConfirmedEvent {
    data object OnContinue : SecurityConfirmedEvent
}