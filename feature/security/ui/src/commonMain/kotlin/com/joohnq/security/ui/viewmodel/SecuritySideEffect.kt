package com.joohnq.security.ui.viewmodel

sealed interface SecuritySideEffect {
    data object OnSecurityUpdated : SecuritySideEffect
    data class ShowError(val error: Throwable) : SecuritySideEffect
}
