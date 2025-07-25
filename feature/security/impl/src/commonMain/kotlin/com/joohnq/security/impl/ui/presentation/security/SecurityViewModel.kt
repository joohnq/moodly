package com.joohnq.security.impl.ui.presentation.security

import androidx.lifecycle.viewModelScope
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.security.api.use_case.UpdateSecurityUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toUiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SecurityViewModel(
    private val getSecurityUseCase: GetSecurityUseCase,
    private val updateSecurityUseCase: UpdateSecurityUseCase,
    initialState: SecurityContract.State = SecurityContract.State(),
) : BaseViewModel<SecurityContract.State, SecurityContract.Intent, SecurityContract.SideEffect>(
    initialState = initialState
), SecurityContract.ViewModel {
    override fun onIntent(intent: SecurityContract.Intent) {
        when (intent) {
            is SecurityContract.Intent.GetSecurity -> getSecurity()
            is SecurityContract.Intent.Update -> updateSecurity(intent.security)
        }
    }

    private fun getSecurity() = viewModelScope.launch {
        val res = getSecurityUseCase().toUiState()

        updateState { it.copy(item = res) }
    }

    private fun updateSecurity(security: Security) = viewModelScope.launch {
        val res = updateSecurityUseCase(security).toUiState()

        res.onSuccess {
            emitEffect(SecurityContract.SideEffect.OnSecurityUpdated)
        }.onFailure {
            emitEffect(SecurityContract.SideEffect.ShowError(it))
        }
    }
}