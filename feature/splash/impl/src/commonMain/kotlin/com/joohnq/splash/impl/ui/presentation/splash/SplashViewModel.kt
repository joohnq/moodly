package com.joohnq.splash.impl.ui.presentation.splash

import androidx.lifecycle.viewModelScope
import com.joohnq.api.entity.User
import com.joohnq.api.use_case.AddUserUseCase
import com.joohnq.preferences.api.use_case.GetUserPreferencesUseCase
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.ui.BaseViewModelWithoutState
import kotlinx.coroutines.launch

class SplashViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val getSecurityUseCase: GetSecurityUseCase,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
) : BaseViewModelWithoutState<SplashContract.Intent, SplashContract.SideEffect>() {
    override fun onIntent(intent: SplashContract.Intent) {
        when (intent) {
            SplashContract.Intent.Init -> init()
        }
    }

    private fun init() {
        viewModelScope.launch {
            try {
                addUserUseCase(User()).getOrThrow()
                val security = getSecurityUseCase().getOrThrow()
                val preferences = getUserPreferencesUseCase().getOrThrow()

                val sideEffect =
                    when {
                        security is Security.Biometric ||
                            security is Security.Pin -> SplashContract.SideEffect.NavigateToUnlock

                        security is Security.Corrupted -> SplashContract.SideEffect.NavigateToSecurityCorrupted
                        !preferences.skipWelcome -> SplashContract.SideEffect.NavigateToWelcome
                        !preferences.skipOnboarding -> SplashContract.SideEffect.NavigateToOnboarding
                        !preferences.skipAuth -> SplashContract.SideEffect.NavigateToAuth
                        !preferences.skipSecurity -> SplashContract.SideEffect.NavigateToSecurity
                        else -> SplashContract.SideEffect.NavigateToDashboard
                    }

                emitEffect(sideEffect)
            } catch (e: Exception) {
                emitEffect(SplashContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
