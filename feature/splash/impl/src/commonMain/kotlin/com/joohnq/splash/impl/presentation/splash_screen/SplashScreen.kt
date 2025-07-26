package com.joohnq.splash.impl.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.api.Security
import com.joohnq.security.impl.ui.presentation.security.SecurityContract
import com.joohnq.security.impl.ui.presentation.security.SecurityViewModel
import com.joohnq.ui.mapper.fold
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel

@Composable
fun SplashScreen(
    onNavigateToWelcome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToAuth: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToUnLock: () -> Unit,
    onNavigateToCorruptedSecurity: () -> Unit
) {
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val preferencesState by preferencesViewModel.state.collectAsState()
    val securityState by securityViewModel.state.collectAsState()

    SideEffect {
        userViewModel.onIntent(UserContract.Intent.Init)

        securityViewModel.onIntent(SecurityContract.Intent.Get)
        preferencesViewModel.onIntent(PreferencesContract.Intent.Get)
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
                    security is Security.Biometric ||
                        security is Security.Pin -> onNavigateToUnLock()

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

    SplashScreenContent()
}
