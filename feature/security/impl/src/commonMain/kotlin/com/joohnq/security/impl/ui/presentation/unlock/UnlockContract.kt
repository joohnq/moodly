package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.ui.focus.FocusRequester
import com.joohnq.security.api.Security
import com.joohnq.ui.UnidirectionalViewModel

sealed interface UnlockContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data object ExecuteBiometricSecurity : SideEffect
    }

    sealed interface Intent {
        data class EnterNumber(
            val number: Int?,
            val index: Int,
        ) : Intent

        data class ChangeFieldFocused(
            val index: Int,
        ) : Intent

        data class RequestFocus(
            val index: Int,
        ) : Intent

        data object KeyboardBack : Intent

        data class ChangeShowBottomSheet(
            val value: Boolean,
        ) : Intent

        data object Action : Intent
    }

    data class State(
        val security: Security = Security.None,
        val showBottomSheet: Boolean = false,
        val code: List<Int?> = (1..4).map { null },
        val focusedIndex: Int? = null,
        val isError: Exception? = null,
        val focusRequesters: List<FocusRequester> = (1..4).map { FocusRequester() },
    )
}