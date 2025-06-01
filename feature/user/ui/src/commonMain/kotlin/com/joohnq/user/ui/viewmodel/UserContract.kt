package com.joohnq.user.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User

sealed interface UserContract {
    sealed interface Intent: UserContract {
        data class UpdateUser(val user: User) : Intent
        data class UpdateUserName(val name: String) : Intent
        data class UpdateUserImageBitmap(val image: ImageBitmap) : Intent
        data class UpdateUserImageDrawable(val drawableResId: Int) : Intent
    }

    sealed interface SideEffect: UserContract {
        data object AvatarSaved : SideEffect
        data object UserNameUpdated : SideEffect
        data object UserUpdated : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val user: UiState<User> = UiState.Idle,
    ): UserContract
}