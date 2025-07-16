package com.joohnq.user.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.ui.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toUiState
import com.joohnq.domain.use_case.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<UserState> =
        MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    private val _sideEffect = Channel<UserSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: UserIntent) {
        when (intent) {
            is UserIntent.GetUser -> getUser()
            is UserIntent.UpdateUser -> updateUser(intent.user)
            is UserIntent.UpdateUserImageBitmap -> updateUserImageBitmap(intent.image)
            is UserIntent.UpdateUserName -> updateUserName(intent.name)
            is UserIntent.UpdateUserImageDrawable -> updateUserImageDrawable(intent.i)
            UserIntent.InitUser -> addUser()
        }
    }

    private fun addUser() = viewModelScope.launch {
        val res = addUserUseCase(User()).toUiState()
        res.onSuccess {
            _sideEffect.send(UserSideEffect.AddedUser)
        }.onFailure {
            _sideEffect.send(UserSideEffect.ShowError(it))
        }
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        val res = updateUserUseCase(user).toUiState()
        res.onSuccess {
            _sideEffect.send(UserSideEffect.UpdatedUser)
        }.onFailure {
            _sideEffect.send(UserSideEffect.ShowError(it))
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
            _sideEffect.send(UserSideEffect.AvatarSavedSuccess)
        }.onFailure {
            _sideEffect.send(UserSideEffect.ShowError(it))
        }
    }

    private fun updateUserImageDrawable(i: Int) = viewModelScope.launch {
        val res = updateUserImageDrawableUseCase(i).toUiState()

        res.onSuccess {
            _sideEffect.send(UserSideEffect.AvatarSavedSuccess)
        }.onFailure {
            _sideEffect.send(UserSideEffect.ShowError(it))
        }
    }

    private fun updateUserName(name: String) = viewModelScope.launch {
        val res = updateUserNameUseCase(name).toUiState()
        res.onSuccess {
            _sideEffect.send(UserSideEffect.UserNameUpdatedSuccess)
        }.onFailure {
            _sideEffect.send(UserSideEffect.ShowError(it))
        }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _state.update { it.copy(user = status) }
    }
}