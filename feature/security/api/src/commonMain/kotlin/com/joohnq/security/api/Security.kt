package com.joohnq.security.api

import kotlinx.serialization.Serializable

@Serializable
sealed class Security {
    @Serializable
    data object None : Security()

    @Serializable
    data object Corrupted : Security()

    @Serializable
    data class Biometric(val enabled: Boolean) : Security()

    @Serializable
    data class Pin(val enabled: Boolean, val code: List<Int>) : Security()
}

fun Security.getPinCode(): List<Int> =
    (this as Security.Pin).code
