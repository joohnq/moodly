package com.joohnq.splash.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.auth.ui.contract.AuthContract
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import com.joohnq.preferences.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityContract
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityViewModel
import com.joohnq.splash.ui.viewmodel.SplashContract
import com.joohnq.splash.ui.viewmodel.SplashViewModel
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    navigateToWelcome: () -> Unit,
    navigateToOnboarding: () -> Unit,
    navigateToAuth: () -> Unit,
    navigateToSecurity: () -> Unit,
    navigateToDashboard: () -> Unit,
    navigateToUnlock: () -> Unit,
    navigateToCorruptedSecurity: () -> Unit,
) {
    val securityViewModel: SecurityViewModel = sharedViewModel()
    val authViewModel: AuthViewModel = sharedViewModel()
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val preferencesState by preferencesViewModel.state.collectAsState()
    val securityState by securityViewModel.state.collectAsState()
    val authState by authViewModel.state.collectAsState()
    val userState by userViewModel.state.collectAsState()
    val viewModel: SplashViewModel = koinViewModel()

    fun onError(message: String) {
    }

    LaunchedEffect(Unit){
//        authViewModel.onIntent(AuthContract.Intent.SignOut)
    }

    LaunchedEffect(Unit) {
        authViewModel.onIntent(AuthContract.Intent.GetAuthUser)
        securityViewModel.onIntent(SecurityContract.Intent.Get)
        preferencesViewModel.onIntent(PreferencesContract.Intent.Get)
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SplashContract.SideEffect.NavigateToWelcome -> navigateToWelcome()
                is SplashContract.SideEffect.NavigateToOnboarding -> navigateToOnboarding()
                is SplashContract.SideEffect.NavigateToAuth -> navigateToAuth()
                is SplashContract.SideEffect.NavigateToSecurity -> navigateToSecurity()
                is SplashContract.SideEffect.NavigateToDashboard -> navigateToDashboard()
                is SplashContract.SideEffect.NavigateToUnlock -> navigateToUnlock()
                is SplashContract.SideEffect.NavigateToCorruptedSecurity -> navigateToCorruptedSecurity()
                is SplashContract.SideEffect.ShowError -> onError(sideEffect.error)
            }
        }
    }


    LaunchedEffect(
        authState.status,
        preferencesState.status,
        securityState.status,
        userState.user
    ) {
        viewModel.onIntent(
            SplashContract.Intent.Initialize(
                auth = authState.status,
                user = userState.user,
                security = securityState.status,
                preferences = preferencesState.status
            )
        )
    }

    SplashScreenUI()
}
