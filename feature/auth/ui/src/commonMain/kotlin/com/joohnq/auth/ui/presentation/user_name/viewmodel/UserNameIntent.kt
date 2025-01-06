package com.joohnq.auth.ui.presentation.user_name.viewmodel

sealed class UserNameIntent {
    data class UpdateUserName(val name: String) : UserNameIntent()
    data class UpdateUserNameError(val error: String?) : UserNameIntent()
    data object ResetState : UserNameIntent()
}