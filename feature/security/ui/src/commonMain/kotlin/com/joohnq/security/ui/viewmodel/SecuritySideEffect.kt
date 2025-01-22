package com.joohnq.security.ui.viewmodel

sealed interface SecuritySideEffect {
    data object OnBiometricFaceIdUpdated : SecuritySideEffect
    data class ShowError(val error: Throwable) : SecuritySideEffect
}
