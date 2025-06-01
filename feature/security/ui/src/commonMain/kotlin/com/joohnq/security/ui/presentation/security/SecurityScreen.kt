package com.joohnq.security.ui.presentation.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.preferences.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityContract
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityViewModel
import com.joohnq.security.ui.securityAuthentication
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
                is SecurityContract.SideEffect.SecurityUpdated -> {
                    securityViewModel.onIntent(SecurityContract.Intent.Get)
                    onNavigateToSecurityConfirmed()
                }

                is SecurityContract.SideEffect.ShowError -> onError(sideEffect.error.message.toString())
            }
        }
    }

    fun onEvent(event: SecurityContract.Event) {
        when (event) {
            SecurityContract.Event.Continue -> {
                if (securityAuthentication.isDeviceHasBiometric()) {
                    securityAuthentication.authenticateWithFace { isAuthorized ->
                        if (isAuthorized) {
                            securityViewModel.onIntent(
                                SecurityContract.Intent.UpdateSecurity(
                                    Security.Biometric(true)
                                )
                            )
                        }
                    }
                }
            }

            SecurityContract.Event.Skip -> {
                preferencesViewModel.onIntent(
                    PreferencesContract.Intent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }

            SecurityContract.Event.SetPin -> onNavigatePIN()
        }
    }

    return SecurityUI(
        snackBarState = snackBarState,
        onEvent = ::onEvent,
    )
}
