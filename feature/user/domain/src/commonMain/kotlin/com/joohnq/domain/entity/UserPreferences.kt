package com.joohnq.domain.entity

data class UserPreferences(
    val id: Int = 1,
    val skipWelcome: Boolean = false,
    val skipOnboarding: Boolean = false,
    val skipAuth: Boolean = false,
)
