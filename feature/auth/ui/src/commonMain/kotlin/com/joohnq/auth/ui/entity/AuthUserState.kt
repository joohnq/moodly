package com.joohnq.auth.ui.entity

import com.joohnq.auth.domain.entity.AuthUser

sealed class AuthUserState {
    data class Authenticated(val user: AuthUser) : AuthUserState()
    data object NotAuthenticated : AuthUserState()
}