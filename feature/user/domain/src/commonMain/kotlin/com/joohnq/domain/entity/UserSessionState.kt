package com.joohnq.domain.entity

data class UserSessionState(
    val authUserId: String? = null,
    val userProfile: User? = null
)