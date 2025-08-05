package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.ui.focus.FocusRequester
import com.joohnq.security.api.Security
import com.joohnq.ui.UnidirectionalViewModel

sealed interface UnlockContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnContinue : Event
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect
    }

    sealed interface Intent {
        data object Init : Intent

        data class OnEnterNumber(
            val number: Int?,
            val index: Int,
        ) : Intent

        data class OnChangeFieldFocused(
            val index: Int,
        ) : Intent

        data class RequestFocus(
            val index: Int,
        ) : Intent

        data class UpdateIsError(
            val error: Exception?,
        ) : Intent

        data object Action : Intent

        data object OnKeyboardBack : Intent

        data class UpdateShowBottomSheet(
            val value: Boolean,
        ) : Intent
    }

    data class State(
        val security: Security = Security.None,
        val showBottomSheet: Boolean = false,
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null,
        val isError: Exception? = null,
        val focusRequesters: List<FocusRequester> = (1..4).map { FocusRequester() },
        val canContinue: Boolean = false,
    )
}
