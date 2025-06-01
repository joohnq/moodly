package com.joohnq.security.ui.presentation.unlock

sealed interface UnLockContract {
    sealed interface Event : UnLockContract {
        data object Continue : Event
        data class UpdateShowBottomSheet(val value: Boolean) : Event
    }
}