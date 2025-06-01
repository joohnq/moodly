package com.joohnq.splash.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.auth.ui.viewmodel.AuthUserState
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.mapper.TripleState
import com.joohnq.domain.mapper.whenAllResolved
import com.joohnq.preferences.domain.entity.AppPreferences
import com.joohnq.security.domain.Security
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _sideEffect = Channel<SplashContract.SideEffect>(Channel.Factory.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(intent: SplashContract.Intent) {
        when (intent) {
            is SplashContract.Intent.Initialize -> initialize(
                auth = intent.auth,
                user = intent.user,
                security = intent.security,
                preferences = intent.preferences,
            )
        }
    }

    private fun initialize(
        auth: UiState<AuthUserState>,
        user: UiState<User>,
        security: UiState<Security>,
        preferences: UiState<AppPreferences>
    ) = viewModelScope.launch {
        if (auth is UiState.Loading || user is UiState.Loading) {
            return@launch
        }

        if (user is UiState.Error) {
            _sideEffect.send(SplashContract.SideEffect.NavigateToAuth)
            return@launch
        }

        try {
            val sideEffect: SplashContract.SideEffect? = TripleState(
                auth,
                preferences,
                security,
            ).whenAllResolved(
                onSuccess = { authState: AuthUserState, preferences: AppPreferences, security: Security ->
                    when {
                        security is Security.Biometric
                                || security is Security.Pin ->
                            SplashContract.SideEffect.NavigateToUnlock

                        security is Security.Corrupted ->
                            SplashContract.SideEffect.NavigateToCorruptedSecurity

                        !preferences.skipWelcome ->
                            SplashContract.SideEffect.NavigateToWelcome

                        !preferences.skipOnboarding ->
                            SplashContract.SideEffect.NavigateToOnboarding

                        authState is AuthUserState.NotAuthenticated ->
                            SplashContract.SideEffect.NavigateToAuth

                        !preferences.skipSecurity ->
                            SplashContract.SideEffect.NavigateToSecurity

                        else ->
                            SplashContract.SideEffect.NavigateToDashboard
                    }
                },
                onError = {error ->
                    SplashContract.SideEffect.ShowError(error.message.toString())
                },
                onLoading = { null }
            )

            sideEffect?.let {
                _sideEffect.send(it)
                return@launch
            }
        } catch (e: Exception) {
            _sideEffect.send(SplashContract.SideEffect.ShowError(e.message.toString()))
        }
    }
}