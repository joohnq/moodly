package com.joohnq.auth.impl.ui.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthNameScreen(
    navigateNext: () -> Unit,
    viewModel: AuthNameViewModel = sharedViewModel(),
) {
    val focusManager: FocusManager = LocalFocusManager.current
    val snackBarState = rememberSnackBarState()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                AuthNameContract.SideEffect.NavigateNext -> navigateNext()
                is AuthNameContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: AuthNameContract.Event) =
        when (event) {
            AuthNameContract.Event.OnClearFocus -> focusManager.clearFocus()
        }

    AuthNameContent(
        state = state,
        snackBarState = snackBarState,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}
