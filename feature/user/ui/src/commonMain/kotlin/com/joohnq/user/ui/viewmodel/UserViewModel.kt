package com.joohnq.user.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.UserSessionManager
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.domain.mapper.toUiState
import com.joohnq.domain.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.domain.use_case.UpdateUserImageDrawableUseCase
import com.joohnq.domain.use_case.UpdateUserNameUseCase
import com.joohnq.domain.use_case.UpdateUserUseCase
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
    private val updateUserUseCase: UpdateUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
    private val userSessionManager: UserSessionManager
) : ViewModel() {
    private val _state: MutableStateFlow<UserContract.State> =
        MutableStateFlow(UserContract.State())
    val state: StateFlow<UserContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<UserContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            userSessionManager.session.collect { sessionState ->
                val userState: UiState<User> = when (sessionState) {
                    is UiState.Idle -> UiState.Idle
                    is UiState.Loading -> UiState.Loading
                    is UiState.Error -> UiState.Error(sessionState.error)
                    is UiState.Success -> {
                        UiState.Success(sessionState.data.userProfile!!)
                    }
                }
                changeUserStatus(userState)
            }
        }
    }

    fun onIntent(intent: UserContract.Intent) {
        when (intent) {
            is UserContract.Intent.UpdateUser -> updateUser(intent.user)
            is UserContract.Intent.UpdateUserImageBitmap -> updateUserImageBitmap(intent.image)
            is UserContract.Intent.UpdateUserName -> updateUserName(intent.name)
            is UserContract.Intent.UpdateUserImageDrawable -> updateUserImageDrawable(intent.drawableResId)
        }
    }

    private fun updateUser(user: User) = viewModelScope.launch {
        val res = updateUserUseCase(user).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.UserUpdated)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUserImageBitmap(image: ImageBitmap) = viewModelScope.launch(Dispatchers.IO) {
        val res = updateUserImageBitmapUseCase(image).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.AvatarSaved)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUserImageDrawable(drawableResId: Int) = viewModelScope.launch {
        val res = updateUserImageDrawableUseCase(drawableResId).toUiState()

        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.AvatarSaved)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun updateUserName(name: String) = viewModelScope.launch {
        val res = updateUserNameUseCase(name).toUiState()
        res.onSuccess {
            _sideEffect.send(UserContract.SideEffect.UserNameUpdated)
        }.onFailure {
            _sideEffect.send(UserContract.SideEffect.ShowError(it))
        }
    }

    private fun changeUserStatus(status: UiState<User>) {
        _state.update { it.copy(user = status) }
    }
}