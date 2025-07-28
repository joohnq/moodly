package com.joohnq.user.impl.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import com.joohnq.api.entity.User
import com.joohnq.api.use_case.AddUserUseCase
import com.joohnq.api.use_case.GetUserUseCase
import com.joohnq.api.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.api.use_case.UpdateUserImageDrawableUseCase
import com.joohnq.api.use_case.UpdateUserNameUseCase
import com.joohnq.api.use_case.UpdateUserUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class UserViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
    initialState: UserContract.State = UserContract.State(),
) : BaseViewModel<UserContract.State, UserContract.Intent, UserContract.SideEffect>(
        initialState = initialState
    ),
    UserContract.ViewModel {
    override fun onIntent(intent: UserContract.Intent) {
        when (intent) {
            is UserContract.Intent.Get -> get()
            is UserContract.Intent.Update -> update(intent.user)
            is UserContract.Intent.UpdateImageBitmap -> updateUserImageBitmap(intent.image)
            is UserContract.Intent.UpdateName -> updateUserName(intent.name)
            is UserContract.Intent.UpdateImageDrawable -> updateUserImageDrawable(intent.i)
            UserContract.Intent.Init -> add()
        }
    }

    private fun add() =
        viewModelScope.launch {
            val res = addUserUseCase(User()).toUiState()

            res
                .onSuccess {
                    emitEffect(UserContract.SideEffect.Added)
                }.onFailure {
                    emitEffect(UserContract.SideEffect.ShowError(it))
                }
        }

    private fun update(user: User) =
        viewModelScope.launch {
            val res = updateUserUseCase(user).toUiState()

            res
                .onSuccess {
                    emitEffect(UserContract.SideEffect.Updated)
                }.onFailure {
                    emitEffect(UserContract.SideEffect.ShowError(it))
                }
        }

    private fun get() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }

            val res = getUserUseCase().toUiState()

            updateState { it.copy(res) }
        }

    private fun updateUserImageBitmap(image: ImageBitmap) =
        viewModelScope.launch(Dispatchers.IO) {
            val res = updateUserImageBitmapUseCase(image).toUiState()

            res
                .onSuccess {
                    emitEffect(UserContract.SideEffect.AvatarSavedSuccess)
                }.onFailure {
                    emitEffect(UserContract.SideEffect.ShowError(it))
                }
        }

    private fun updateUserImageDrawable(i: Int) =
        viewModelScope.launch {
            val res = updateUserImageDrawableUseCase(i).toUiState()

            res
                .onSuccess {
                    emitEffect(UserContract.SideEffect.AvatarSavedSuccess)
                }.onFailure {
                    emitEffect(UserContract.SideEffect.ShowError(it))
                }
        }

    private fun updateUserName(name: String) =
        viewModelScope.launch {
            val res = updateUserNameUseCase(name).toUiState()

            res
                .onSuccess {
                    emitEffect(UserContract.SideEffect.UserNameUpdatedSuccess)
                }.onFailure {
                    emitEffect(UserContract.SideEffect.ShowError(it))
                }
        }
}
