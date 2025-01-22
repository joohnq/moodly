package com.joohnq.security.ui.viewmodel

sealed interface SecurityIntent {
    data object GetSecurity : SecurityIntent
    data object UpdateSecurity : SecurityIntent
    data class SetAddingBiometricFaceIdSecurity(val enabled: Boolean) : SecurityIntent
    data class SetAddingPINSecurity(val enabled: Boolean, val code: List<Int>) : SecurityIntent
}