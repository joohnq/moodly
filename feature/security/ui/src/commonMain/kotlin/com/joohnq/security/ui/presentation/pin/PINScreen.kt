package com.joohnq.security.ui.presentation.pin

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.state.PINState
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModel
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelIntent

class PINScreen(
    private val onNavigateToDashboard: () -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<PINState>() {
    @Composable
    override fun Screen(): PINState {
        val pinViewModel: PINViewModel = sharedViewModel()
        val state by pinViewModel.state.collectAsState()
        val snackBarState = remember { SnackbarHostState() }
        val focusRequesters = remember {
            List(4) { FocusRequester() }
        }
        val focusManager = LocalFocusManager.current
        val keyboardManager = LocalSoftwareKeyboardController.current

        LaunchedEffect(state.focusedIndex) {
            state.focusedIndex?.let { i ->
                focusRequesters.getOrNull(i)?.requestFocus()
            }
        }

        LaunchedEffect(state.code) {
            val allNumbersEntered = state.code.none { it == null }
            if (allNumbersEntered) {
                focusRequesters.forEach {
                    it.freeFocus()
                }
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        }

        fun onEvent(event: PINEvent) {
            when (event) {
                PINEvent.OnContinue -> {
                    onNavigateToDashboard()
                }

                PINEvent.OnGoBack -> onGoBack()
                PINEvent.OnClearFocus -> {
                    focusManager.clearFocus()
                    keyboardManager?.hide()
                }
            }
        }

        return PINState(
            snackBarState = snackBarState,
            onEvent = ::onEvent,
            pinViewModelState = state,
            onAction = { action ->
                if (action is PINViewModelIntent.OnEnterNumber && action.number != null) {
                    focusRequesters[action.index].freeFocus()
                }
                pinViewModel.onAction(action)
            },
            focusRequesters = focusRequesters
        )
    }

    @Composable
    override fun UI(state: PINState) = PINUI(state)
}