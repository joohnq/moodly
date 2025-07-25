package com.joohnq.security.impl.ui.presentation.unlock

sealed interface UnlockContract {
    sealed interface Event {
        data object OnContinue : Event
        data class UpdateShowBottomSheet(val value: Boolean) : Event
    }
}