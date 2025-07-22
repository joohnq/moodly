package com.joohnq.security.impl.ui.presentation.security_confirmed.event

sealed interface SecurityConfirmedEvent {
    data object OnContinue : SecurityConfirmedEvent
}