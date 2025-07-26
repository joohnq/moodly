package com.joohnq.security.impl.ui.presentation.unlock

sealed interface UnlockContract {
    sealed interface Event {
        data object OnContinue : Event

        data class OnUpdateShowBottomSheet(
            val value: Boolean
        ) : Event
    }
}
