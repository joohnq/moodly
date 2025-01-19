package com.joohnq.security.ui.presentation.security

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.ui.presentation.security.event.SecurityEvent
import com.joohnq.security.ui.presentation.security.state.SecurityState
import com.joohnq.security.ui.securityAuthentication
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

class SecurityScreen(
    private val onNavigateToSecurityConfirmed: () -> Unit,
    private val onNavigateToDashboard: () -> Unit,
    private val onNavigatePIN: () -> Unit,
) : CustomScreen<SecurityState>() {
    @Composable
    override fun Screen(): SecurityState {
        val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val securityAuthentication: SecurityAuthentication = securityAuthentication()

        fun onEvent(event: SecurityEvent) {
            when (event) {
                SecurityEvent.OnContinue -> {
                    if (securityAuthentication.isDeviceHasBiometric()) {
                        securityAuthentication.authenticateWithFace { isAuthorized ->
                            if (isAuthorized) {
                                onNavigateToSecurityConfirmed()
                            }
                        }
                    }
                }

                SecurityEvent.OnSkip -> {
                    userPreferencesViewModel.onAction(
                        UserPreferenceViewModelIntent.UpdateSkipAuth()
                    )
                    onNavigateToDashboard()
                }

                SecurityEvent.OnSetPin -> onNavigatePIN()
            }
        }

        return SecurityState(
            snackBarState = snackBarState,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: SecurityState) = SecurityUI(state)
}