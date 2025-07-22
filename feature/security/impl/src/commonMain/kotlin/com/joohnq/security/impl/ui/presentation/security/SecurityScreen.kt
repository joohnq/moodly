package com.joohnq.security.ui.presentation.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.preferences.impl.ui.viewmodel.PreferenceIntent
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.ui.presentation.security.event.SecurityEvent
import com.joohnq.security.ui.securityAuthentication
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecuritySideEffect
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun SecurityScreen(
    onNavigateToSecurityConfirmed: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigatePIN: () -> Unit,
) {
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val securityAuthentication: SecurityAuthentication = securityAuthentication()
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()

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
                    onNavigateToSecurityConfirmed()
                }

                is SecuritySideEffect.ShowError -> onError(sideEffect.error)
            }
        }
    }

    fun onEvent(event: SecurityEvent) {
        when (event) {
            SecurityEvent.OnContinue -> {
                if (securityAuthentication.isDeviceHasBiometric()) {
                    securityAuthentication.authenticateWithFace { isAuthorized ->
                        if (isAuthorized) {
                            securityViewModel.onAction(
                                SecurityIntent.UpdateSecurity(
                                    Security.Biometric(true)
                                )
                            )
                        }
                    }
                }
            }

            SecurityEvent.OnSkip -> {
                preferencesViewModel.onAction(
                    PreferenceIntent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }

            SecurityEvent.OnSetPin -> onNavigatePIN()
        }
    }

    return SecurityUI(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
    )
}
