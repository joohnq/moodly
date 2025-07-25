package com.joohnq.auth.impl.presentation.auth

import com.joohnq.ui.UnidirectionalViewModel

sealed interface AuthNameContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class Update(val name: String) : Intent
        data class UpdateError(val error: String?) : Intent
        data object ResetState : Intent
    }

    sealed interface SideEffect

    data class State(
        val name: String = "",
        val nameError: String? = null,
    )

    sealed interface Event {
        data object Continue : Event
    }
}