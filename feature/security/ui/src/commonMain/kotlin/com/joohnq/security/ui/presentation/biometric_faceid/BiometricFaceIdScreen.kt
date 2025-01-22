package com.joohnq.security.ui.presentation.biometric_faceid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.ui.presentation.biometric_faceid.event.BiometricFaceIdEvent
import com.joohnq.security.ui.securityAuthentication
import kotlinx.coroutines.launch

@Composable
fun BiometricFaceIdScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val securityAuthentication: SecurityAuthentication = securityAuthentication()
    val scope = rememberCoroutineScope()

    fun onEvent(event: BiometricFaceIdEvent) {
        when (event) {
            BiometricFaceIdEvent.OnContinue -> {
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
        }
    }

    return BiometricFaceIdUI(
        onEvent = ::onEvent
    )
}