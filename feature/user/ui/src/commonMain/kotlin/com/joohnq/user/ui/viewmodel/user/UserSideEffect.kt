package com.joohnq.user.ui.viewmodel.user

sealed interface UserSideEffect {
    data object AvatarSavedSuccess : UserSideEffect
    data object UserNameUpdatedSuccess : UserSideEffect
    data object UpdatedUser : UserSideEffect
    data object AddedUser : UserSideEffect
    data class ShowError(val error: Throwable) : UserSideEffect
}
