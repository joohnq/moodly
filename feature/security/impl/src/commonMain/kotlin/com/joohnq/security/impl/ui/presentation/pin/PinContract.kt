package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.ui.focus.FocusRequester
import com.joohnq.ui.UnidirectionalViewModel

sealed interface PinContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnClearFocus : Event

        data object OnGoBack : Event
    }

    sealed interface Intent {
        data class OnEnterNumber(
            val number: Int?,
            val index: Int,
        ) : Intent

        data class OnChangeFieldFocused(
            val index: Int,
        ) : Intent

        data object Action : Intent

        data object OnKeyboardBack : Intent
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
