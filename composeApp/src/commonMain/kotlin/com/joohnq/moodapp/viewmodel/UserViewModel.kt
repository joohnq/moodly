package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.UserRepository
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserState(
    val user: UiState<User> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val updating: UiState<Boolean> = UiState.Idle,
)

sealed class UserIntent {
    data object InitUser : UserIntent()
    data object GetUser : UserIntent()
    data class UpdateUser(val user: User) : UserIntent()
    data class UpdateUserName(val name: String) : UserIntent()
    data object ResetUpdatingStatus : UserIntent()
}

class UserViewModel(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userState: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    fun onAction(intent: UserIntent) {
        when (intent) {
            is UserIntent.InitUser -> initUser()
            is UserIntent.GetUser -> getUser()
            is UserIntent.UpdateUser -> updateUser(intent.user)
            is UserIntent.UpdateUserName -> updateUserName(intent.name)
            UserIntent.ResetUpdatingStatus -> changeUpdatingStatus(UiState.Idle)
        }
    }

    private fun initUser() = viewModelScope.launch(dispatcher) {
        userRepository.initUser()
    }

    private fun updateUser(user: User) = viewModelScope.launch(dispatcher) {
        changeUpdatingStatus(UiState.Loading)

        val res = userRepository.updateUser(user)

        changeUpdatingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Failure to set user"
            )
        )
    }

    private fun getUser() = viewModelScope.launch(dispatcher)
    {
        changeUserStatus(UiState.Loading)

        try {
            val user = userRepository.getUser()
            changeUserStatus(UiState.Success(user))
        } catch (e: Exception) {
            changeUserStatus(UiState.Error(e.message.toString()))
        }
    }

    private fun updateUserName(name: String) = viewModelScope.launch(dispatcher) {
        changeUpdatingStatus(UiState.Loading)

        val res = userRepository.updateUserName(name)

        changeUpdatingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Failure to set user"
            )
        )
    }

    private fun changeUpdatingStatus(status: UiState<Boolean>) {
        _userState.update { it.copy(updating = status) }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _userState.update { it.copy(user = status) }
    }
}