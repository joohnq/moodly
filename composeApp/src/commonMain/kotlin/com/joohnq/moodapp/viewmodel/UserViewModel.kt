package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.model.repository.UserRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserAdding(
    val status: UiState<Boolean> = UiState.Idle
)

data class UserUpdating(
    val status: UiState<Boolean> = UiState.Idle,
    val name: String = "",
    val nameError: String = ""
)

data class UserState(
    val user: UiState<User> = UiState.Idle,
    val adding: UserAdding = UserAdding(),
    val updating: UserUpdating = UserUpdating()
)

class UserViewModel(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userState: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    fun initUser() = viewModelScope.launch(dispatcher) {
        userRepository.initUser()
    }

    fun updateUser(user: User) = viewModelScope.launch(dispatcher) {
        changeUpdatingStatus(UiState.Loading)

        val res = userRepository.updateUser(user)

        changeUpdatingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Failure to set user"
            )
        )
    }

    fun getUser() = viewModelScope.launch(dispatcher)
    {
        _userState.update { it.copy(user = UiState.Loading) }

        try {
            val user = userRepository.getUser()
            _userState.update { it.copy(user = UiState.Success(user)) }
        } catch (e: Exception) {
            _userState.update { it.copy(user = UiState.Error(e.message.toString())) }
        }
    }

    fun updateUserName() = viewModelScope.launch(dispatcher) {
        val name = userState.value.updating.name
        if (name.trim().isEmpty()) {
            _userState.update { it.copy(updating = it.updating.copy(nameError = "Name is required")) }
            return@launch
        }

        changeUpdatingStatus(UiState.Loading)

        val res = userRepository.updateUserName(name)

        changeUpdatingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Failure to set user"
            )
        )
    }

    fun setUpdatingUserName(name: String) {
        _userState.update {
            it.copy(updating = it.updating.copy(name = name, nameError = ""))
        }
    }

    fun resetUpdatingStatus() {
        _userState.update { it.copy(updating = UserUpdating()) }
    }

    private fun changeUpdatingStatus(status: UiState<Boolean>) {
        _userState.update { it.copy(updating = it.updating.copy(status = status)) }
    }
}