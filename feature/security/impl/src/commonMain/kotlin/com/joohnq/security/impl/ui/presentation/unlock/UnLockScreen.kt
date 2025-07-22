package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.ui.mapper.getValueOrNull
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.api.getPinCode
import com.joohnq.security.impl.ui.presentation.pin.viewmodel.PINIntent
import com.joohnq.security.impl.ui.presentation.pin.viewmodel.PINViewModel
import com.joohnq.security.impl.ui.presentation.unlock.event.UnLockEvent
import com.joohnq.security.impl.ui.securityAuthentication
import com.joohnq.security.impl.ui.viewmodel.SecurityViewModel
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
    val pinViewModel: PINViewModel = sharedViewModel()
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
    val securityType = securityState.item.getValueOrNull()
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

    fun onEvent(event: UnLockEvent) {
        when (event) {
            UnLockEvent.OnContinue -> {
                when (securityType) {
                    is Security.Pin -> onEvent(UnLockEvent.UpdateShowBottomSheet(true))
                    else -> executeBiometricSecurity()
                }
            }

            is UnLockEvent.UpdateShowBottomSheet -> {
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
        onAction = { action ->
            if (action is PINIntent.OnEnterNumber && action.number != null) {
                focusRequesters[action.index].freeFocus()
            }
            pinViewModel.onAction(action)
        },
    )
}