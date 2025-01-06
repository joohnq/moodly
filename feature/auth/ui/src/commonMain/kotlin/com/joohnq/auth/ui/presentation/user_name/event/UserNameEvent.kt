package com.joohnq.auth.ui.presentation.user_name.event

sealed class UserNameEvent {
    data object Continue : UserNameEvent()
}