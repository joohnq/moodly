package com.joohnq.preferences.domain.entity

data class AppPreferences(
    val skipWelcome: Boolean = false,
    val skipOnboarding: Boolean = false,
    val skipAuth: Boolean = false,
    val skipSecurity: Boolean = false,
)