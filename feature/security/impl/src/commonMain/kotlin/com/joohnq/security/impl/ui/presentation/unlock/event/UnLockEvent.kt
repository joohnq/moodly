package com.joohnq.security.impl.ui.presentation.unlock.event

sealed interface UnLockEvent {
    data object OnContinue : UnLockEvent
    data class UpdateShowBottomSheet(val value: Boolean) : UnLockEvent
}