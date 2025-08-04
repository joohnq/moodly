package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun PinScreen(
    navigateNext: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: PinViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackBarState = rememberSnackBarState()
    val focusRequesters = rememberFocusRequester(4)
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                PinContract.SideEffect.NavigateNext -> navigateNext()
                is PinContract.SideEffect.ShowError -> snackBarState.showSnackbar(sideEffect.message)
            }
        }
    }

    fun onEvent(event: PinContract.Event) {
        when (event) {
            PinContract.Event.OnContinue -> {
                viewModel.onIntent(PinContract.Intent.Action)
            }

            PinContract.Event.OnGoBack -> onGoBack()
            PinContract.Event.OnClearFocus -> {
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        }
    }

    return PinContent(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
        state = state,
        onIntent = { action ->
            if (action is PinContract.Intent.OnEnterNumber && action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
            viewModel.onIntent(action)
        },
        focusRequesters = focusRequesters,
        focusManager = focusManager,
        keyboardManager = keyboardManager
    )
}
