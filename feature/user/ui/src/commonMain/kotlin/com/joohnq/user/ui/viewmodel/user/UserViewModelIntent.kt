package com.joohnq.user.ui.viewmodel.user

import com.joohnq.domain.entity.User

sealed class UserViewModelIntent {
    data object InitUser : UserViewModelIntent()
    data object GetUser : UserViewModelIntent()
    data class UpdateUser(val user: User) : UserViewModelIntent()
    data class UpdateUserName(val name: String) : UserViewModelIntent()
    data object ResetUpdatingStatus : UserViewModelIntent()
}
