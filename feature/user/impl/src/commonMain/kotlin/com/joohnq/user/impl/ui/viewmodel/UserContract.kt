package com.joohnq.user.impl.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.api.entity.User
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface UserContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object GetUser : Intent
        data object InitUser : Intent
        data class Update(val user: User) : Intent
        data class UpdateName(val name: String) : Intent
        data class UpdateImageBitmap(val image: ImageBitmap) : Intent
        data class UpdateImageDrawable(val i: Int) : Intent
    }

    sealed interface SideEffect {
        data object AvatarSavedSuccess : SideEffect
        data object UserNameUpdatedSuccess : SideEffect
        data object UpdatedUser : SideEffect
        data object AddedUser : SideEffect
        data class ShowError(val error: String) : SideEffect
    }

    data class State(
        val user: UiState<User> = UiState.Idle,
    )
}