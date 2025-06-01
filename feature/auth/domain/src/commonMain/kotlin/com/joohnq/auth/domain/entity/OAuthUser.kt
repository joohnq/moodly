package com.joohnq.auth.domain.entity

data class OAuthUser(
    val id: String?,
    val token: String?,
    val accessToken: String?,
    val email: String?,
    val name: String?,
    val image: String?
)