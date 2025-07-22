package com.joohnq.auth.impl.presentation.user_name.event

sealed interface UserNameEvent {
    data object Continue : UserNameEvent
}