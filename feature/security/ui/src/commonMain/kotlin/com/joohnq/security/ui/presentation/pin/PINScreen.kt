package com.joohnq.security.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.domain.mapper.itemsNotNull
import com.joohnq.security.domain.Security
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINIntent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModel
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecuritySideEffect
import com.joohnq.security.ui.viewmodel.SecurityViewModel
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
    val pinViewModel: PINViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val state by pinViewModel.state.collectAsState()
    val snackBarState = rememberSnackBarState()
    val focusRequesters = rememberFocusRequester(4)
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    val canContinue by derivedStateOf {
        state.code.none { it == null }
    }

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    LaunchedEffect(securityViewModel.sideEffect) {
        securityViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SecuritySideEffect.OnSecurityUpdated -> {
                    securityViewModel.onAction(SecurityIntent.GetSecurity)
                    onNavigateToDashboard()
                }

                is SecuritySideEffect.ShowError -> onError(sideEffect.error.message.toString())
            }
        }
    }

    fun onEvent(event: PINEvent) {
        when (event) {
            PINEvent.OnContinue -> {
                securityViewModel.onAction(
                    SecurityIntent.UpdateSecurity(
                        Security.Pin(
                            enabled = true,
                            code = state.code.itemsNotNull()
                        )
                    )
                )
            }

            PINEvent.OnGoBack -> onGoBack()
            PINEvent.OnClearFocus -> {
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        }
    }

    return PINUI(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
        state = state,
        onAction = { action ->
            if (action is PINIntent.OnEnterNumber && action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
            pinViewModel.onAction(action)
        },
        focusRequesters = focusRequesters,
        canContinue = canContinue,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
    )
}