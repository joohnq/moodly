package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.impl.ui.securityAuthentication
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnLockScreen(
    onNavigateToDashboard: () -> Unit,
    viewModel: UnLockViewModel = sharedViewModel(),
    securityAuthentication: SecurityAuthentication = securityAuthentication(),
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                UnlockContract.SideEffect.NavigateNext -> onNavigateToDashboard()
            }
        }

    fun executeBiometricSecurity() {
        if (securityAuthentication.isDeviceHasBiometric()) {
            securityAuthentication.authenticateWithFace { isAuthorized ->
                if (isAuthorized) {
                    scope.launch {
                        onNavigateToDashboard()
                    }
                }
            }
        }
    }

    fun onEvent(event: UnlockContract.Event) {
        when (event) {
            UnlockContract.Event.OnContinue -> {
                when (state.security) {
                    is Security.Pin ->
                        viewModel.onIntent(
                            UnlockContract.Intent.UpdateShowBottomSheet(
                                true
                            )
                        )

                    else -> executeBiometricSecurity()
                }
            }
        }
    }

    LaunchedEffect(sheetState.isVisible) {
        if (!sheetState.isVisible) return@LaunchedEffect

        viewModel.onIntent(UnlockContract.Intent.RequestFocus(0))
    }

    UnLockContent(
        sheetState = sheetState,
        state = state,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
        onEvent = ::onEvent,
        onIntent = dispatch
    )
}
