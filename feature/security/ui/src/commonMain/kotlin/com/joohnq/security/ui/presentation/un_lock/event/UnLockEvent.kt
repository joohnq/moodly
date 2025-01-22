package com.joohnq.security.ui.presentation.un_lock.event

sealed interface UnLockEvent {
    data object OnContinue : UnLockEvent
    data class UpdateShowBottomSheet(val value: Boolean) : UnLockEvent
}