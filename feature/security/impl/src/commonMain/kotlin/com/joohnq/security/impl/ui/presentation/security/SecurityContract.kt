package com.joohnq.security.impl.ui.presentation.security

import com.joohnq.security.api.Security
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface SecurityContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object Skip : Intent

        data class Action(
            val security: Security,
        ) : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data object Skip : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val item: UiState<Security> = UiState.Idle,
    )

    sealed interface Event {
        data object Authenticate : Event

        data object SetPin : Event
    }
}