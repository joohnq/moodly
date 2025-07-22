package com.joohnq.security.impl.ui.viewmodel

sealed interface SecuritySideEffect {
    data object OnSecurityUpdated : SecuritySideEffect
    data class ShowError(val error: String) : SecuritySideEffect
}
