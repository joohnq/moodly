package com.joohnq.splash.ui.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.security.domain.Security
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

@Composable
fun SplashScreen(
    onNavigateToWelcome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToAuth: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToUnLock: () -> Unit,
    onNavigateToCorruptedSecurity: () -> Unit,
) {
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()
    val securityState by securityViewModel.state.collectAsState()

    SideEffect {
        userPreferencesViewModel.onAction(UserPreferenceIntent.AddUserPreferences)
        userViewModel.onAction(UserIntent.InitUser)

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
        ).fold(
            onSuccess = { preferences: UserPreferences, security: Security ->
                when {
                    security is Security.Biometric
                            || security is Security.Pin -> onNavigateToUnLock()

                    security is Security.Corrupted -> onNavigateToCorruptedSecurity()
                    !preferences.skipWelcome -> onNavigateToWelcome()
                    !preferences.skipOnboarding -> onNavigateToOnboarding()
                    !preferences.skipAuth -> onNavigateToAuth()
                    !preferences.skipSecurity -> onNavigateToSecurity()
                    else -> onNavigateToDashboard()
                }
            }
        )
    }

    SplashScreenUI()
}
