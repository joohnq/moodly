package com.joohnq.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val id: Int = 1,
    val skipWelcomeScreen: Boolean = false,
    val skipOnboardingScreen: Boolean = false,
    val skipGetUserNameScreen: Boolean = false,
) {
    companion object {
        fun init(): UserPreferences = UserPreferences()
    }
}
