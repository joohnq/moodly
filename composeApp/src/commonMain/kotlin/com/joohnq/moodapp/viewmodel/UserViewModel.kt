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

data class UserState(
    val user: UiState<User> = UiState.Idle,
    val addingStatus: UiState<Boolean> = UiState.Idle,
    val updatingStatus: UiState<Boolean> = UiState.Idle
)

class UserViewModel(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userState: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    fun initUser() = viewModelScope.launch(dispatcher) {
        val res = userRepository.initUser()
    }

    fun updateUser(user: User) = viewModelScope.launch(dispatcher) {
        _userState.update { it.copy(addingStatus = UiState.Loading) }

        val res = userRepository.updateUser(user)

        _userState.update {
            it.copy(
                addingStatus = if (res) UiState.Success(true) else UiState.Error(
                    "Failure to set user"
                )
            )
        }
    }


    fun getUser() = viewModelScope.launch(dispatcher) {
        _userState.update {
            it.copy(user = UiState.Loading)
        }
        try {
            val user = userRepository.getUser()
            _userState.update {
                it.copy(user = UiState.Success(user))
            }
        } catch (e: Exception) {
            _userState.update {
                it.copy(user = UiState.Error(e.message.toString()))
            }
        }
    }

    fun updateUserName(name: String) = viewModelScope.launch(dispatcher) {
        val res = userRepository.updateUserName(name)

        _userState.update {
            it.copy(
                updatingStatus = if (res) UiState.Success(true) else UiState.Error(
                    "Failure to set user"
                )
            )
        }
    }
}