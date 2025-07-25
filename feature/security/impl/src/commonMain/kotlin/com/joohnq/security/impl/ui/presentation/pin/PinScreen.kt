package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.api.mapper.itemsNotNull
import com.joohnq.security.api.Security
import com.joohnq.security.impl.ui.presentation.security.SecurityContract
import com.joohnq.security.impl.ui.presentation.security.SecurityViewModel
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun PINScreen(
    onNavigateToDashboard: () -> Unit,
    onGoBack: () -> Unit,
) {
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val pinViewModel: PinViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val state by pinViewModel.state.collectAsState()
    val snackBarState = rememberSnackBarState()
    val focusRequesters = rememberFocusRequester(4)
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    LaunchedEffect(securityViewModel.sideEffect) {
        securityViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SecurityContract.SideEffect.OnSecurityUpdated -> {
                    securityViewModel.onIntent(SecurityContract.Intent.GetSecurity)
                    onNavigateToDashboard()
                }

                is SecurityContract.SideEffect.ShowError -> onError(sideEffect.error)
            }
        }
    }

    fun onEvent(event: PinContract.Event) {
        when (event) {
            PinContract.Event.OnContinue -> {
                securityViewModel.onIntent(
                    SecurityContract.Intent.Update(
                        Security.Pin(
                            enabled = true,
                            code = state.code.itemsNotNull()
                        )
                    )
                )
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
        onAction = { action ->
            if (action is PinContract.Intent.OnEnterNumber && action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
            pinViewModel.onIntent(action)
        },
        focusRequesters = focusRequesters,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
    )
}