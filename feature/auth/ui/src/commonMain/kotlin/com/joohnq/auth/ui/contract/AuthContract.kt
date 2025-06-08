package com.joohnq.auth.ui.contract

import com.joohnq.auth.domain.entity.OAuthUser
import com.joohnq.auth.ui.entity.AuthUserState
import com.joohnq.domain.entity.UiState

sealed interface AuthContract {
    data class State(
        val status: UiState<AuthUserState> = UiState.Idle
    )

    data class SignInState(
        val email: String = "",
        val password: String = "",
        val status: UiState<Unit> = UiState.Idle
    )

    data class SignUpState(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val status: UiState<Unit> = UiState.Idle
    )

    sealed interface Event {
        data object NavigateToForgotPassword : Event
        data object NavigateToSignIn : Event
        data object NavigateToSignUp : Event
    }

    sealed interface Intent {
        data object GetAuthUser : Intent
        data object SignIn : Intent
        data object SignInWithGoogle : Intent
        data object SignUp : Intent
        data object SignOut : Intent

        data class SignInEmailChanged(val email: String) : Intent
        data class SignInPasswordChanged(val password: String) : Intent

        data class SignUpNameChanged(val name: String) : Intent
        data class SignUpEmailChanged(val email: String) : Intent
        data class SignUpPasswordChanged(val password: String) : Intent
    }

    sealed interface SideEffect {
        data object SignOutFailure : SideEffect
        data object SignOutSuccess : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }
}