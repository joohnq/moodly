package com.joohnq.auth.ui.presentation.avatar.event

sealed class AvatarEvent {
    data object OnPickAvatar : AvatarEvent()
    data object OnContinue : AvatarEvent()
}