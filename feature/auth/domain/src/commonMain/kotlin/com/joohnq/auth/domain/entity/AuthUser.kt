package com.joohnq.auth.domain.entity

data class AuthUser(
    val id: String,
    val email: String? = null,
    val name: String? = null,
)