package com.joohnq.security.impl.ui.presentation.security

import com.joohnq.security.api.Security
import com.joohnq.ui.entity.UiState

sealed interface SecurityContract {
    sealed interface Intent {
        data object GetSecurity : Intent
        data class Update(val security: Security) : Intent
    }

    sealed interface SideEffect {
        data object OnSecurityUpdated : SideEffect
        data class ShowError(val error: String) : SideEffect
    }

    data class State(
        val item: UiState<Security> = UiState.Idle,
    )

    sealed interface Event {
        data object OnContinue : Event
        data object OnSetPin : Event
        data object OnSkip : Event
    }
}