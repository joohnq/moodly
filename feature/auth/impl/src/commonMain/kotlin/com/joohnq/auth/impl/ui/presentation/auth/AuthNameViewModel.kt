package com.joohnq.auth.impl.ui.presentation.auth

import com.joohnq.ui.BaseViewModel

class AuthNameViewModel(
    initialState: AuthNameContract.State = AuthNameContract.State(),
) : BaseViewModel<AuthNameContract.State, AuthNameContract.Intent, AuthNameContract.SideEffect>(
        initialState = initialState
    ),
    AuthNameContract.ViewModel {
    override fun onIntent(intent: AuthNameContract.Intent) {
        when (intent) {
            is AuthNameContract.Intent.Update -> updateState { it.copy(name = intent.name) }
            AuthNameContract.Intent.ResetState -> updateState { AuthNameContract.State() }
            is AuthNameContract.Intent.UpdateError -> updateState { it.copy(nameError = intent.error) }
        }
    }
}
