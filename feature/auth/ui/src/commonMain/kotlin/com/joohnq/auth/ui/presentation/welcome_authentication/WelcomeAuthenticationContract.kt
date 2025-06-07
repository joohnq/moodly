package com.joohnq.auth.ui.presentation.welcome_authentication

sealed interface WelcomeAuthenticationContract {
    sealed interface Event: WelcomeAuthenticationContract {
        object SignInWithEmail : Event
        object SignUp : Event
        object SignInWithGoogle : Event
    }
}