package com.joohnq.security.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.domain.getPinCode
import com.joohnq.security.ui.presentation.pin.viewmodel.PINIntent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModel
import com.joohnq.security.ui.presentation.unlock.event.UnLockEvent
import com.joohnq.security.ui.securityAuthentication
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.shared_resources.remember.rememberFocusRequester
import kotlinx.coroutines.launch

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
                isError = Exception("Invalid PIN")
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