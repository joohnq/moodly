package com.joohnq.splash.impl.presentation.splash_screen

import androidx.compose.runtime.*
import com.joohnq.ui.mapper.fold
import com.joohnq.ui.sharedViewModel
import com.joohnq.preferences.domain.entity.AppPreferences
import com.joohnq.preferences.impl.viewmodel.PreferenceIntent
import com.joohnq.preferences.impl.viewmodel.PreferencesViewModel
import com.joohnq.security.api.Security
import com.joohnq.security.ui.viewmodel.SecurityIntent
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import com.joohnq.user.ui.viewmodel.UserIntent
import com.joohnq.user.ui.viewmodel.UserViewModel

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
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val preferencesState by preferencesViewModel.state.collectAsState()
    val securityState by securityViewModel.state.collectAsState()

    SideEffect {
        userViewModel.onAction(UserIntent.InitUser)

        securityViewModel.onAction(SecurityIntent.GetSecurity)
        preferencesViewModel.onAction(PreferenceIntent.GetPreferences)
    }

    LaunchedEffect(
        preferencesState.userPreferences,
        securityState
    ) {
        listOf(
            preferencesState.userPreferences,
            securityState.item
        ).fold(
            onSuccess = { preferences: AppPreferences, security: Security ->
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
