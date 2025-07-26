package com.joohnq.security.impl.ui.presentation.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.impl.ui.securityAuthentication
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
                is SecurityContract.SideEffect.OnSecurityUpdated -> {
                    securityViewModel.onIntent(SecurityContract.Intent.Get)
                    onNavigateToSecurityConfirmed()
                }

                is SecurityContract.SideEffect.ShowError -> onError(sideEffect.error)
            }
        }
    }

    fun onEvent(event: SecurityContract.Event) {
        when (event) {
            SecurityContract.Event.OnContinue -> {
                if (securityAuthentication.isDeviceHasBiometric()) {
                    securityAuthentication.authenticateWithFace { isAuthorized ->
                        if (isAuthorized) {
                            securityViewModel.onIntent(
                                SecurityContract.Intent.Update(
                                    Security.Biometric(true)
                                )
                            )
                        }
                    }
                }
            }

            SecurityContract.Event.OnSkip -> {
                preferencesViewModel.onIntent(
                    PreferencesContract.Intent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }

            SecurityContract.Event.OnSetPin -> onNavigatePIN()
        }
    }

    return SecurityContent(
        snackBarState = snackBarState,
        onEvent = ::onEvent
    )
}
