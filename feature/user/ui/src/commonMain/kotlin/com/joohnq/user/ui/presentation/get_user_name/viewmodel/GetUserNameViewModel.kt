package com.joohnq.user.ui.presentation.get_user_name.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GetUserNameViewModel : ViewModel() {
    private val _state: MutableStateFlow<GetUserNameViewModelState> =
        MutableStateFlow(GetUserNameViewModelState())
    val state: StateFlow<GetUserNameViewModelState> = _state

    fun onAction(intent: GetUserNameIntent) {
        when (intent) {
            is GetUserNameIntent.UpdateUserName -> updateUserName(intent.name)
            GetUserNameIntent.ResetState -> resetState()
            is GetUserNameIntent.UpdateUserNameError -> updateUserNameError(intent.error)
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
        _state.update { GetUserNameViewModelState() }
    }
}