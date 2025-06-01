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
import com.joohnq.security.ui.presentation.pin.viewmodel.PinContract
import com.joohnq.security.ui.presentation.pin.viewmodel.PinViewModel
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityContract
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityViewModel
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
                is SecurityContract.SideEffect.SecurityUpdated -> {
                    securityViewModel.onIntent(SecurityContract.Intent.Get)
                    onNavigateToDashboard()
                }

                is SecurityContract.SideEffect.ShowError -> onError(sideEffect.error.message.toString())
            }
        }
    }

    fun onEvent(event: PinContract.Event) {
        when (event) {
            PinContract.Event.Continue -> {
                securityViewModel.onIntent(
                    SecurityContract.Intent.UpdateSecurity(
                        Security.Pin(
                            enabled = true,
                            code = state.code.itemsNotNull()
                        )
                    )
                )
            }

            PinContract.Event.GoBack -> onGoBack()
            PinContract.Event.ClearFocus -> {
                focusManager.clearFocus()
                keyboardManager?.hide()
            }
        }
    }

    return PINUI(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
        state = state,
        onIntent = { intent ->
            if (intent is PinContract.Intent.EnterNumber && intent.number != null) {
                focusRequesters[intent.index].freeFocus()
            }
            pinViewModel.onIntent(intent)
        },
        focusRequesters = focusRequesters,
        canContinue = canContinue,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
    )
}