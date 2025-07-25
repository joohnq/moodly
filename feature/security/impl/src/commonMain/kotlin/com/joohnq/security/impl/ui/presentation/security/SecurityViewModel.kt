package com.joohnq.security.impl.ui.presentation.security

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.ui.mapper.toUiState
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.security.api.use_case.UpdateSecurityUseCase
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SecurityViewModel(
    private val getSecurityUseCase: GetSecurityUseCase,
    private val updateSecurityUseCase: UpdateSecurityUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<SecurityContract.State> =
        MutableStateFlow(SecurityContract.State())
    val state: StateFlow<SecurityContract.State> = _state

    private val _sideEffect = Channel<SecurityContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: SecurityContract.Intent) {
        when (intent) {
            is SecurityContract.Intent.GetSecurity -> getSecurity()
            is SecurityContract.Intent.Update -> updateSecurity(intent.security)
        }
    }

    private fun getSecurity() = viewModelScope.launch {
        val res = getSecurityUseCase().toUiState()
        _state.update { it.copy(item = res) }
    }

    private fun updateSecurity(security: Security) = viewModelScope.launch {
        val res = updateSecurityUseCase(security).toUiState()
        res.onSuccess {
            _sideEffect.send(SecurityContract.SideEffect.OnSecurityUpdated)
        }.onFailure {
            _sideEffect.send(SecurityContract.SideEffect.ShowError(it))
        }

    }
}