package com.joohnq.auth.impl.ui.presentation.auth

import com.joohnq.ui.UnidirectionalViewModel

sealed interface AuthNameContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class UpdateName(
            val name: String,
        ) : Intent

        data class UpdateNameError(
            val error: String?,
        ) : Intent

        data object Action : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val message: String,
        ) : SideEffect

        data object NavigateNext : SideEffect
    }

    data class State(
        val name: String = "",
        val nameError: String? = null,
    )

    sealed interface Event {
        data object OnContinue : Event

        data object OnClearFocus : Event
    }
}
