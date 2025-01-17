package com.joohnq.user.ui.viewmodel.user

sealed class UserSideEffect {
    data object AvatarSavedSuccess : UserSideEffect()
    data object UserNameUpdatedSuccess : UserSideEffect()
    data class ShowError(val error: Throwable) : UserSideEffect()
}
