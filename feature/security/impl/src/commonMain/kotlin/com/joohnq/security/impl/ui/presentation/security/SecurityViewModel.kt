package com.joohnq.security.impl.ui.presentation.security

import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.security.api.use_case.UpdateSecurityUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toUiState
import kotlinx.coroutines.launch

class SecurityViewModel(
    private val getSecurityUseCase: GetSecurityUseCase,
    private val updateSecurityUseCase: UpdateSecurityUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
    initialState: SecurityContract.State = SecurityContract.State(),
) : BaseViewModel<SecurityContract.State, SecurityContract.Intent, SecurityContract.SideEffect>(
        initialState = initialState
    ),
    SecurityContract.ViewModel {
    override fun onIntent(intent: SecurityContract.Intent) {
        when (intent) {
            is SecurityContract.Intent.Get -> getSecurity()
            is SecurityContract.Intent.Update -> updateSecurity(intent.security)
            SecurityContract.Intent.Skip -> skip()
        }
    }

    private fun getSecurity() =
        viewModelScope.launch {
            val res = getSecurityUseCase().toUiState()

            updateState { it.copy(item = res) }
        }

    private fun skip() {
        viewModelScope.launch {
            try {
                updateSkipOnboardingUseCase(true).getOrThrow()
                emitEffect(SecurityContract.SideEffect.Skip)
            } catch (e: Exception) {
                emitEffect(SecurityContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun updateSecurity(security: Security) =
        viewModelScope.launch {
            try {
                updateSecurityUseCase(security).getOrThrow()

                emitEffect(SecurityContract.SideEffect.OnSecurityUpdated)
            } catch (e: Exception) {
                emitEffect(SecurityContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
