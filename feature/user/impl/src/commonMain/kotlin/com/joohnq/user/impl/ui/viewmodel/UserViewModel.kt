package com.joohnq.user.impl.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.api.entity.User
import com.joohnq.api.use_case.AddUserUseCase
import com.joohnq.api.use_case.GetUserUseCase
import com.joohnq.api.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.api.use_case.UpdateUserImageDrawableUseCase
import com.joohnq.api.use_case.UpdateUserNameUseCase
import com.joohnq.api.use_case.UpdateUserUseCase
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<UserContract.State> =
        MutableStateFlow(UserContract.State())
    val state: StateFlow<UserContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<UserContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: UserContract.Intent) {
        when (intent) {
            is UserContract.Intent.GetUser -> getUser()
            is UserContract.Intent.Update -> updateUser(intent.user)
            is UserContract.Intent.UpdateImageBitmap -> updateUserImageBitmap(intent.image)
            is UserContract.Intent.UpdateName -> updateUserName(intent.name)
            is UserContract.Intent.UpdateImageDrawable -> updateUserImageDrawable(intent.i)
            UserContract.Intent.InitUser -> addUser()
        }
    }

    private fun addUser() = viewModelScope.launch {
        val res = addUserUseCase(User()).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.AddedUser)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        val res = updateUserUseCase(user).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.UpdatedUser)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun getUser() = viewModelScope.launch {
        changeUserStatus(UiState.Loading)
        val res = getUserUseCase().toUiState()
        changeUserStatus(res)
    }

    private fun updateUserImageBitmap(image: ImageBitmap) = viewModelScope.launch(Dispatchers.IO) {
        val res = updateUserImageBitmapUseCase(image).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.AvatarSavedSuccess)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUserImageDrawable(i: Int) = viewModelScope.launch {
        val res = updateUserImageDrawableUseCase(i).toUiState()

        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.AvatarSavedSuccess)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUserName(name: String) = viewModelScope.launch {
        val res = updateUserNameUseCase(name).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.UserNameUpdatedSuccess)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _state.update { it.copy(user = status) }
    }
}