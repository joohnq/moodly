package com.joohnq.security.ui.presentation.unlock.event

sealed interface UnLockEvent {
    data object OnContinue : UnLockEvent
    data class UpdateShowBottomSheet(val value: Boolean) : UnLockEvent
}