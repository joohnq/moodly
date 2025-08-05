package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun PinScreen(
    navigateNext: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: PinViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                PinContract.SideEffect.NavigateNext -> navigateNext()
                is PinContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: PinContract.Event) {
        when (event) {
            PinContract.Event.OnGoBack -> onGoBack()
            PinContract.Event.OnClearFocus -> {
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        }
    }

    PinContent(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
        state = state,
        onIntent = dispatch,
        focusManager = focusManager,
        keyboardManager = keyboardManager
    )
}
