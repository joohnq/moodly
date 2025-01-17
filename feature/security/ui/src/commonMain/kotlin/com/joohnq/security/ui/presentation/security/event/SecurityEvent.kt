package com.joohnq.security.ui.presentation.security.event

sealed class SecurityEvent {
    data object OnContinue : SecurityEvent()
    data object OnSkip : SecurityEvent()
}