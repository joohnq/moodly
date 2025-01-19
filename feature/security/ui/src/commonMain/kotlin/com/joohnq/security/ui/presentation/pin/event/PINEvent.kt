package com.joohnq.security.ui.presentation.pin.event

sealed interface PINEvent {
    data object OnContinue : PINEvent
    data object OnGoBack : PINEvent
}