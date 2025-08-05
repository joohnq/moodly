package com.joohnq.security.impl.ui.presentation.security

import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.UpdateSecurityUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class SecurityViewModel(
    private val updateSecurityUseCase: UpdateSecurityUseCase,
    private val updateSkipSecurityUseCase: UpdateSkipSecurityUseCase,
    initialState: SecurityContract.State = SecurityContract.State(),
) : BaseViewModel<SecurityContract.State, SecurityContract.Intent, SecurityContract.SideEffect>(
        initialState = initialState
    ),
    SecurityContract.ViewModel {
    override fun onIntent(intent: SecurityContract.Intent) {
        when (intent) {
            is SecurityContract.Intent.Action -> updateSecurity(intent.security)
            SecurityContract.Intent.Skip -> skip()
        }
    }

    private fun skip() {
        viewModelScope.launch {
            try {
                updateSkipSecurityUseCase(true).getOrThrow()
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

                emitEffect(SecurityContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(SecurityContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}