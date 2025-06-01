package com.joohnq.security.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.domain.mapper.getValue
import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.domain.getPinCode
import com.joohnq.security.ui.presentation.pin.viewmodel.PinContract
import com.joohnq.security.ui.presentation.pin.viewmodel.PinViewModel
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityViewModel
import com.joohnq.security.ui.securityAuthentication
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.invalid_pin
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnLockScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val pinViewModel: PinViewModel = sharedViewModel()
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val securityAuthentication: SecurityAuthentication = securityAuthentication()
    val securityState by securityViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val pinState by pinViewModel.state.collectAsState()
    val focusRequesters = rememberFocusRequester(4)
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    val securityType = securityState.status.getValue()
    val canContinue by derivedStateOf {
        pinState.code.none { it == null }
    }
    var isError by remember { mutableStateOf<Exception?>(null) }
    val invalidPin = stringResource(Res.string.invalid_pin)

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

    fun onEvent(event: UnLockContract.Event) {
        when (event) {
            UnLockContract.Event.Continue -> {
                when (securityType) {
                    is Security.Pin -> onEvent(UnLockContract.Event.UpdateShowBottomSheet(true))
                    else -> executeBiometricSecurity()
                }
            }

            is UnLockContract.Event.UpdateShowBottomSheet -> {
                showBottomSheet = event.value
            }
        }
    }

    LaunchedEffect(sheetState.isVisible) {
        if (sheetState.isVisible) {
            focusRequesters[0].requestFocus()
        }
    }

    LaunchedEffect(pinState.code) {
        isError = null
    }

    LaunchedEffect(canContinue) {
        if (canContinue) {
            val pin = securityType.getPinCode()
            if (pin != pinState.code) {
                isError = Exception(invalidPin)
            } else {
                onNavigateToDashboard()
            }
        }
    }

    return UnLockUI(
        sheetState = sheetState,
        isError = isError,
        showBottomSheet = showBottomSheet,
        onEvent = ::onEvent,
        pinState = pinState,
        focusRequesters = focusRequesters,
        focusManager = focusManager,
        keyboardManager = keyboardManager,
        onIntent = { intent ->
            if (intent is PinContract.Intent.EnterNumber && intent.number != null) {
                focusRequesters[intent.index].freeFocus()
            }
            pinViewModel.onIntent(intent)
        },
    )
}