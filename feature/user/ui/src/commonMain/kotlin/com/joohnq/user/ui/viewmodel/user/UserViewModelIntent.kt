package com.joohnq.user.ui.viewmodel.user

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.domain.entity.User

sealed class UserViewModelIntent {
    data object InitUser : UserViewModelIntent()
    data object GetUser : UserViewModelIntent()
    data class UpdateUser(val user: User) : UserViewModelIntent()
    data class UpdateUserName(val name: String) : UserViewModelIntent()
    data class UpdateUserImageBitmap(val image: ImageBitmap) : UserViewModelIntent()
    data class UpdateUserImageDrawable(val i: Int) : UserViewModelIntent()
    data object ResetUpdatingStatus : UserViewModelIntent()
}
