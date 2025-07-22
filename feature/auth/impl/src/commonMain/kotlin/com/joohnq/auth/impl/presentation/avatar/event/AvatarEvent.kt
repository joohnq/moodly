package com.joohnq.auth.impl.presentation.avatar.event

sealed interface AvatarEvent {
    data object OnPickAvatar : AvatarEvent
    data object OnContinue : AvatarEvent
}