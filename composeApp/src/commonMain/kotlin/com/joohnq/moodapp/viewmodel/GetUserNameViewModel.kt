package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class GetUserNameViewModelState(
    val name: String = "",
    val nameError: String? = null
)

sealed class GetUserNameIntent {
    data class UpdateUserName(val name: String) : GetUserNameIntent()
    data class UpdateUserNameError(val error: String?) : GetUserNameIntent()
    data object ResetState : GetUserNameIntent()
}

class GetUserNameViewModel : ViewModel() {
    private val _getUserNameState: MutableStateFlow<GetUserNameViewModelState> =
        MutableStateFlow(GetUserNameViewModelState())
    val getUserNameState: StateFlow<GetUserNameViewModelState> = _getUserNameState

    fun onAction(intent: GetUserNameIntent) {
        when (intent) {
            is GetUserNameIntent.UpdateUserName -> updateUserName(intent.name)
            GetUserNameIntent.ResetState -> resetState()
            is GetUserNameIntent.UpdateUserNameError -> updateUserNameError(intent.error)
        }
    }

    private fun updateUserName(name: String) {
        _getUserNameState.update {
            it.copy(name = name, nameError = null)
        }
    }

    private fun updateUserNameError(error: String?) {
        _getUserNameState.update {
            it.copy(nameError = error)
        }
    }

    private fun resetState() {
        _getUserNameState.update { GetUserNameViewModelState() }
    }
}