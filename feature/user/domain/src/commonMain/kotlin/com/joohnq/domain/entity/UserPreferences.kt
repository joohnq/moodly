package com.joohnq.domain.entity

data class UserPreferences(
    val id: Int = 1,
    val skipWelcomeScreen: Boolean = false,
    val skipOnboardingScreen: Boolean = false,
    val skipGetUserNameScreen: Boolean = false,
)
