package com.joohnq.auth.ui.presentation.user_name.event

sealed interface UserNameEvent {
    data object Continue : UserNameEvent
}