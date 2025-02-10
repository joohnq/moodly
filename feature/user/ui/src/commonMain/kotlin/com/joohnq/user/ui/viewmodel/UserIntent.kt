package com.joohnq.user.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.domain.entity.User

sealed interface UserIntent {
    data object GetUser : UserIntent
    data object InitUser : UserIntent
    data class UpdateUser(val user: User) : UserIntent
    data class UpdateUserName(val name: String) : UserIntent
    data class UpdateUserImageBitmap(val image: ImageBitmap) : UserIntent
    data class UpdateUserImageDrawable(val i: Int) : UserIntent
}
