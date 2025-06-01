package com.joohnq.security.ui.presentation.security.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.security.domain.Security

sealed interface SecurityContract {
    sealed interface Intent : SecurityContract {
        data object Get : Intent
        data class UpdateSecurity(val security: Security) : Intent
    }

    sealed interface SideEffect : SecurityContract {
        data object SecurityUpdated : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val status: UiState<Security> = UiState.Idle,
    ) : SecurityContract

    sealed interface Event : SecurityContract {
        data object Continue : Event
        data object SetPin : Event
        data object Skip : Event
    }
}