package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.api.getPinCode
import com.joohnq.security.impl.ui.securityAuthentication
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.invalid_pin
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnLockScreen(
    onNavigateToDashboard: () -> Unit,
    viewModel: UnLockViewModel = sharedViewModel(),
    securityAuthentication: SecurityAuthentication = securityAuthentication(),
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    val canContinue by derivedStateOf { state.code.none { it == null } }
    val invalidPin = stringResource(Res.string.invalid_pin)

    LaunchedEffect(Unit) {
        viewModel.onIntent(UnlockContract.Intent.Init)

        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is UnlockContract.SideEffect.ShowError -> {}
            }
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
        if (sheetState.isVisible) {
            viewModel.onIntent(UnlockContract.Intent.RequestFocus(0))
        }
    }

    LaunchedEffect(canContinue) {
        if (canContinue) {
            val pin = state.security.getPinCode()
            if (pin != state.code) {
                viewModel.onIntent(UnlockContract.Intent.UpdateIsError(Exception(invalidPin)))
            } else {
                onNavigateToDashboard()
            }
        }
    }

    return UnLockContent(
        sheetState = sheetState,
        state = state,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
