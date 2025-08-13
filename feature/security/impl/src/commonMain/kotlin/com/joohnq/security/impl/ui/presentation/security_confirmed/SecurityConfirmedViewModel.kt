package com.joohnq.security.impl.ui.presentation.security_confirmed

import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.ui.BaseViewModelWithoutState
import kotlinx.coroutines.launch

class SecurityConfirmedViewModel(
    private val updateSkipSecurityUseCase: UpdateSkipSecurityUseCase,
) : BaseViewModelWithoutState<SecurityConfirmedContract.Intent, SecurityConfirmedContract.SideEffect>(),
    SecurityConfirmedContract.ViewModel {
    override fun onIntent(intent: SecurityConfirmedContract.Intent) {
        when (intent) {
            SecurityConfirmedContract.Intent.Action -> action()
        }
    }

    private fun action() {
        viewModelScope.launch {
            try {
                updateSkipSecurityUseCase(true).getOrThrow()
                emitEffect(SecurityConfirmedContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(SecurityConfirmedContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
