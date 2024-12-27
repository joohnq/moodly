package com.joohnq.user.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.User
import com.joohnq.domain.use_case.user.GetUserUseCase
import com.joohnq.domain.use_case.user.InitUserUseCase
import com.joohnq.domain.use_case.user.UpdateUserNameUseCase
import com.joohnq.domain.use_case.user.UpdateUserUseCase
import com.joohnq.mood.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val initUserUseCase: InitUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,

    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state: MutableStateFlow<UserViewModelState> =
        MutableStateFlow(UserViewModelState())
    val state: StateFlow<UserViewModelState> = _state

    fun onAction(intent: UserViewModelIntent) {
        when (intent) {
            is UserViewModelIntent.InitUser -> initUser()
            is UserViewModelIntent.GetUser -> getUser()
            is UserViewModelIntent.UpdateUser -> updateUser(intent.user)
            is UserViewModelIntent.UpdateUserName -> updateUserName(intent.name)
            UserViewModelIntent.ResetUpdatingStatus -> changeUpdatingStatus(UiState.Idle)
        }
    }

    private fun initUser() = viewModelScope.launch(dispatcher) {
        initUserUseCase()
    }

    private fun updateUser(user: User) = viewModelScope.launch(dispatcher) {
        changeUpdatingStatus(UiState.Loading)

        val res = updateUserUseCase(user)

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
            val user = getUserUseCase()
            changeUserStatus(UiState.Success(user))
        } catch (e: Exception) {
            changeUserStatus(UiState.Error(e.message.toString()))
        }
    }

    private fun updateUserName(name: String) = viewModelScope.launch(dispatcher) {
        changeUpdatingStatus(UiState.Loading)

        val res = updateUserNameUseCase(name)

        changeUpdatingStatus(
            if (res) UiState.Success(true) else UiState.Error(
                "Failure to set user"
            )
        )
    }

    private fun changeUpdatingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(updating = status) }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _state.update { it.copy(user = status) }
    }
}