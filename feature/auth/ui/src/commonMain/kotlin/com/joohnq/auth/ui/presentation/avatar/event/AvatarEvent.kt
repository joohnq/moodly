package com.joohnq.auth.ui.presentation.avatar.event

sealed interface AvatarEvent {
    data object OnPickAvatar : AvatarEvent
    data object OnContinue : AvatarEvent
}