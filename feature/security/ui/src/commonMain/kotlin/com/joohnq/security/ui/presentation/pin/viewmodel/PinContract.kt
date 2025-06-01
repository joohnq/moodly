package com.joohnq.security.ui.presentation.pin.viewmodel

sealed interface PinContract {
    sealed interface Intent : PinContract {
        data class EnterNumber(val number: Int?, val index: Int) : Intent
        data class ChangeFieldFocused(val index: Int) : Intent
        data object KeyboardBack : Intent
    }

    data class State(
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null,
    ) : PinContract

    sealed interface Event: PinContract {
        data object Continue : Event
        data object ClearFocus : Event
        data object GoBack : Event
    }
}