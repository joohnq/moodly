package com.joohnq.auth.ui.presentation.user_name.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserNameViewModel : ViewModel() {
    private val _state: MutableStateFlow<UserNameState> =
        MutableStateFlow(UserNameState())
    val state: StateFlow<UserNameState> = _state

    fun onAction(intent: UserNameIntent) {
        when (intent) {
            is UserNameIntent.UpdateUserName -> updateUserName(intent.name)
            UserNameIntent.ResetState -> resetState()
            is UserNameIntent.UpdateUserNameError -> updateUserNameError(intent.error)
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

    private fun resetState() {
        _state.update { UserNameState() }
    }
}