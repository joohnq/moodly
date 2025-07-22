package com.joohnq.auth.impl.presentation.user_name.viewmodel

sealed interface UserNameIntent {
    data class UpdateUserName(val name: String) : UserNameIntent
    data class UpdateUserNameError(val error: String?) : UserNameIntent
    data object ResetState : UserNameIntent
}