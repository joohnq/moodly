package com.joohnq.auth.impl.presentation.auth

sealed interface AuthNameContract {
    sealed interface Intent {
        data class Update(val name: String) : Intent
        data class UpdateError(val error: String?) : Intent
        data object ResetState : Intent
    }

    data class State(
        val name: String = "",
        val nameError: String? = null,
    )

    sealed interface Event {
        data object Continue : Event
    }
}