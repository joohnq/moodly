package com.joohnq.auth.ui.viewmodel

import com.joohnq.auth.domain.entity.AuthUser

sealed class AuthUserState {
    data class Authenticated(val user: AuthUser) : AuthUserState()
    data object NotAuthenticated : AuthUserState()
}