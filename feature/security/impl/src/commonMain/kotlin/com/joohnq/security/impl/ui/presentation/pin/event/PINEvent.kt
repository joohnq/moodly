package com.joohnq.security.impl.ui.presentation.pin.event

sealed interface PINEvent {
    data object OnContinue : PINEvent
    data object OnClearFocus : PINEvent
    data object OnGoBack : PINEvent
}