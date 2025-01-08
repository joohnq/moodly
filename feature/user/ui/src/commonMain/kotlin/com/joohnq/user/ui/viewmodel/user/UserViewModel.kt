package com.joohnq.user.ui.viewmodel.user

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.use_case.user.GetUserUseCase
import com.joohnq.domain.use_case.user.InitUserUseCase
import com.joohnq.domain.use_case.user.UpdateUserImageBitmapUseCase
import com.joohnq.domain.use_case.user.UpdateUserImageDrawableUseCase
import com.joohnq.domain.use_case.user.UpdateUserNameUseCase
import com.joohnq.domain.use_case.user.UpdateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

class UserViewModel(
    private val initUserUseCase: InitUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<UserViewModelState> =
        MutableStateFlow(UserViewModelState())
    val state: StateFlow<UserViewModelState> = _state

    fun onAction(intent: UserViewModelIntent) {
        when (intent) {
            is UserViewModelIntent.InitUser -> initUser()
            is UserViewModelIntent.GetUser -> getUser()
            is UserViewModelIntent.UpdateUser -> updateUser(intent.user)
            is UserViewModelIntent.UpdateUserImageBitmap -> updateUserImageBitmap(intent.image)
            is UserViewModelIntent.UpdateUserName -> updateUserName(intent.name)
            UserViewModelIntent.ResetUpdatingStatus -> changeUpdatingStatus(UiState.Idle)
            is UserViewModelIntent.UpdateUserImageDrawable -> updateUserImageDrawable(intent.drawable)
        }
    }

    private fun initUser() = viewModelScope.launch {
        initUserUseCase()
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        changeUpdatingStatus(UiState.Loading)
        val res = updateUserUseCase(user).toUiState()
        changeUpdatingStatus(res)
    }

    private fun getUser() = viewModelScope.launch {
        changeUserStatus(UiState.Loading)

        try {
            val user = getUserUseCase().toUiState()
            changeUserStatus(user)
        } catch (e: Exception) {
            changeUserStatus(UiState.Error(e.message.toString()))
        }
    }

    private fun updateUserImageBitmap(image: ImageBitmap) = viewModelScope.launch {
        changeUpdatingStatus(UiState.Loading)
        val res = updateUserImageBitmapUseCase(image).toUiState()
        changeUpdatingStatus(res)
    }

    private fun updateUserImageDrawable(drawable: DrawableResource) = viewModelScope.launch {
        changeUpdatingStatus(UiState.Loading)
        val res = updateUserImageDrawableUseCase(drawable).toUiState()
        changeUpdatingStatus(res)
    }

    private fun updateUserName(name: String) = viewModelScope.launch {
        changeUpdatingStatus(UiState.Loading)
        val res = updateUserNameUseCase(name).toUiState()
        changeUpdatingStatus(res)
    }

    private fun changeUpdatingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(updating = status) }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _state.update { it.copy(user = status) }
    }
}