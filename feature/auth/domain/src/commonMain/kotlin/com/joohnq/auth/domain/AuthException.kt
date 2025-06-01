package com.joohnq.auth.domain

sealed class AuthException(override val message: String): Exception(message) {
    data object FailedToCreateUser : AuthException("Failed to create user")
    data object GoogleUserIsNull : AuthException("Google user is null")
    data object UserNotFound : AuthException("User not found")
}