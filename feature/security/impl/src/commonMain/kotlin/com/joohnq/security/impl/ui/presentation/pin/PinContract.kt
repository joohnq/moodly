package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.ui.focus.FocusRequester
import com.joohnq.ui.UnidirectionalViewModel

sealed interface PinContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object ClearFocus : Event

        data object GoBack : Event
    }

    sealed interface Intent {
        data class EnterNumber(
            val number: Int?,
            val index: Int,
        ) : Intent

        data class ChangeFieldFocused(
            val index: Int,
        ) : Intent

        data object Action : Intent

        data object KeyboardBack : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null,
        val focusRequesters: List<FocusRequester> = (1..4).map { FocusRequester() },
    )
}