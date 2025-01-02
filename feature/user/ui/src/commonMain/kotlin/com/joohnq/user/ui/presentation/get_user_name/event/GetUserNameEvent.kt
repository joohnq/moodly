package com.joohnq.user.ui.presentation.get_user_name.event

sealed class GetUserNameEvent {
    data object Continue : GetUserNameEvent()
}