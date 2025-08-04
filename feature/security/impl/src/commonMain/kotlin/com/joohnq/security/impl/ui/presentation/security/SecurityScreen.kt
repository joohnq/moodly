package com.joohnq.security.impl.ui.presentation.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.impl.ui.securityAuthentication
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun SecurityScreen(
    onNavigateToSecurityConfirmed: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigatePIN: () -> Unit,
    viewModel: SecurityViewModel = sharedViewModel(),
    securityAuthentication: SecurityAuthentication = securityAuthentication(),
) {
    val snackBarState = rememberSnackBarState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SecurityContract.SideEffect.OnSecurityUpdated ->
                    onNavigateToSecurityConfirmed()

                is SecurityContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                SecurityContract.SideEffect.Skip ->
                    onNavigateToDashboard()
            }
        }
    }

    fun onEvent(event: SecurityContract.Event) {
        when (event) {
            SecurityContract.Event.OnContinue -> {
                if (securityAuthentication.isDeviceHasBiometric()) {
                    securityAuthentication.authenticateWithFace { isAuthorized ->
                        if (isAuthorized) {
                            viewModel.onIntent(
                                SecurityContract.Intent.Update(
                                    Security.Biometric(true)
                                )
                            )
                        }
                    }
                }
            }

            SecurityContract.Event.OnSetPin -> onNavigatePIN()
        }
    }

    return SecurityContent(
        snackBarState = snackBarState,
        onEvent = ::onEvent
    )
}
