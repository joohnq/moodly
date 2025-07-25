package com.joohnq.auth.impl.presentation.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthNameViewModel : ViewModel() {
    private val _state: MutableStateFlow<AuthNameContract.State> =
        MutableStateFlow(AuthNameContract.State())
    val state: StateFlow<AuthNameContract.State> = _state

    fun onAction(intent: AuthNameContract.Intent) {
        when (intent) {
            is AuthNameContract.Intent.Update -> updateUserName(intent.name)
            AuthNameContract.Intent.ResetState -> _state.update { AuthNameContract.State() }
            is AuthNameContract.Intent.UpdateError -> updateUserNameError(intent.error)
        }
    }

    private fun updateUserName(name: String) {
        _state.update {
            it.copy(name = name, nameError = null)
        }
    }

    private fun updateUserNameError(error: String?) {
        _state.update {
            it.copy(nameError = error)
        }
    }
}