package com.joohnq.splash.ui.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.mapper.allSuccess
import com.joohnq.core.ui.mapper.anyError
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.domain.Security
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

@Composable
fun SplashScreen(
    onNavigateToWelcome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToAuth: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToBiometricFaceId: () -> Unit,
    onNavigateToPIN: () -> Unit,
    onNavigateToCorruptedSecurity: () -> Unit,
) {
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()
    val securityState by securityViewModel.state.collectAsState()

    SideEffect {
        securityViewModel.onAction(SecurityIntent.GetSecurity)
        userPreferencesViewModel.onAction(UserPreferenceIntent.GetUserPreferences)
    }

    LaunchedEffect(
        userPreferencesState.userPreferences,
        securityState
    ) {
        listOf(
            userPreferencesState.userPreferences,
            securityState.item
        ).allSuccess {
            val security = securityState.item.getValueOrNull()
            val preferences = userPreferencesState.userPreferences.getValueOrNull()

            when (security) {
                is Security.Biometric -> {
                    onNavigateToBiometricFaceId()
                    return@allSuccess
                }

                is Security.Pin -> {
                    onNavigateToPIN()
                    return@allSuccess
                }

                is Security.Corrupted -> {
                    onNavigateToCorruptedSecurity()
                    return@allSuccess
                }

                else -> Unit
            }

            when (false) {
                preferences.skipWelcome -> onNavigateToWelcome()
                preferences.skipOnboarding -> onNavigateToOnboarding()
                preferences.skipAuth -> onNavigateToAuth()
                preferences.skipSecurity -> onNavigateToSecurity()
                else -> onNavigateToDashboard()
            }
        }.anyError {
            println("Errrrrrrro $it")
        }
    }

    SplashScreenUI()
}
