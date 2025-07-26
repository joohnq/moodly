package com.joohnq.security.impl.ui.presentation.pin

import com.joohnq.ui.UnidirectionalViewModel

sealed interface PinContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnContinue : Event

        data object OnClearFocus : Event

        data object OnGoBack : Event
    }

    sealed interface Intent {
        data class OnEnterNumber(
            val number: Int?,
            val index: Int
        ) : Intent

        data class OnChangeFieldFocused(
            val index: Int
        ) : Intent

        data object OnKeyboardBack : Intent
    }

    sealed interface SideEffect

    data class State(
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null
    )
}
