package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.model.repository.UserRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Idle)
    val user: StateFlow<UiState<User>> = _user

    private val _addingStatus: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Idle)
    val addingStatus: StateFlow<UiState<Boolean>> = _addingStatus

    private val _updatingStatus: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Idle)
    val updatingStatus: StateFlow<UiState<Boolean>> = _updatingStatus

    fun initUser() = viewModelScope.launch(dispatcher) {
        val res = userRepository.initUser()
    }

    fun updateUser(user: User) = viewModelScope.launch(dispatcher) {
        _updatingStatus.value = UiState.Loading

        val res = userRepository.updateUser(user)

        _updatingStatus.value = if (res) UiState.Success(true) else UiState.Error(
            "Failure to set user"
        )
    }

    fun getUser() = viewModelScope.launch(dispatcher)
    {
        _user.value = UiState.Loading

        try {
            val user = userRepository.getUser()
            _user.value = UiState.Success(user)
        } catch (e: Exception) {
            _user.value = UiState.Error(e.message.toString())
        }
    }

    fun updateUserName(name: String) = viewModelScope.launch(dispatcher) {
        _updatingStatus.value = UiState.Loading

        val res = userRepository.updateUserName(name)

        _updatingStatus.value = if (res) UiState.Success(true) else UiState.Error(
            "Failure to set user"
        )
    }

    fun resetUpdatingStatus() {
        _updatingStatus.value = UiState.Idle
    }
}