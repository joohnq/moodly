package com.joohnq.security.domain

sealed interface SecurityResult {
    data object HardwareUnavailable : SecurityResult
    data object FeatureUnavailable : SecurityResult
    data class AuthenticationError(val error: String) : SecurityResult
    data object AuthenticationFailed : SecurityResult
    data object AuthenticationSuccess : SecurityResult
    data object AuthenticationNotSet : SecurityResult
}
