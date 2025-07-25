package com.joohnq.security.impl.ui.presentation.pin

sealed interface PinContract {
    sealed interface Event {
        data object OnContinue : Event
        data object OnClearFocus : Event
        data object OnGoBack : Event
    }

    sealed interface Intent {
        data class OnEnterNumber(val number: Int?, val index: Int) : Intent
        data class OnChangeFieldFocused(val index: Int) : Intent
        data object OnKeyboardBack : Intent
    }

    data class State(
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null,
    )
}