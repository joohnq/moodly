package com.joohnq.user.ui.presentation.get_user_name.viewmodel

sealed class GetUserNameIntent {
    data class UpdateUserName(val name: String) : GetUserNameIntent()
    data class UpdateUserNameError(val error: String?) : GetUserNameIntent()
    data object ResetState : GetUserNameIntent()
}