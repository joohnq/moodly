package com.joohnq.user.impl.ui.viewmodel

sealed interface UserSideEffect {
    data object AvatarSavedSuccess : UserSideEffect
    data object UserNameUpdatedSuccess : UserSideEffect
    data object UpdatedUser : UserSideEffect
    data object AddedUser : UserSideEffect
    data class ShowError(val error: String) : UserSideEffect
}
