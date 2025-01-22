package com.joohnq.security.ui.presentation.biometric_faceid.event

sealed interface BiometricFaceIdEvent {
    data object OnContinue : BiometricFaceIdEvent
}