package com.joohnq.security.ui.presentation.security

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.ui.presentation.security.event.SecurityEvent
import com.joohnq.security.ui.securityAuthentication
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecuritySideEffect
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import kotlinx.coroutines.launch

@Composable
fun SecurityScreen(
    onNavigateToSecurityConfirmed: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigatePIN: () -> Unit,
) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val snackBarState = remember { SnackbarHostState() }
    val securityAuthentication: SecurityAuthentication = securityAuthentication()
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()

    fun onError(error: Throwable) {
        scope.launch {
            snackBarState.showSnackbar(error.message.toString())
        }
    }

    LaunchedEffect(securityViewModel.sideEffect) {
        securityViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SecuritySideEffect.OnBiometricFaceIdUpdated -> {
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
                                SecurityIntent.SetAddingBiometricFaceIdSecurity(true)
                            )
                            securityViewModel.onAction(SecurityIntent.UpdateSecurity)
                        }
                    }
                }
            }

            SecurityEvent.OnSkip -> {
                userPreferencesViewModel.onAction(
                    UserPreferenceIntent.UpdateSkipAuth()
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
