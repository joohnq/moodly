package com.joohnq.splash.impl.ui.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.joohnq.ui.sharedViewModel

@Composable
fun SplashScreen(
    onNavigateToWelcome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToAuth: () -> Unit,
    onNavigateToSecurity: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToUnLock: () -> Unit,
    onNavigateToSecurityCorrupted: () -> Unit,
    viewModel: SplashViewModel = sharedViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.onIntent(SplashContract.Intent.Init)

        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                SplashContract.SideEffect.NavigateToWelcome -> onNavigateToWelcome()
                SplashContract.SideEffect.NavigateToOnboarding -> onNavigateToOnboarding()
                SplashContract.SideEffect.NavigateToAuth -> onNavigateToAuth()
                SplashContract.SideEffect.NavigateToSecurity -> onNavigateToSecurity()
                SplashContract.SideEffect.NavigateToDashboard -> onNavigateToDashboard()
                SplashContract.SideEffect.NavigateToUnlock -> onNavigateToUnLock()
                SplashContract.SideEffect.NavigateToSecurityCorrupted -> onNavigateToSecurityCorrupted()
                is SplashContract.SideEffect.ShowError -> {}
            }
        }
    }

    SplashContent()
}
