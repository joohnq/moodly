package com.joohnq.security.ui.presentation.security.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.domain.mapper.toUiState
import com.joohnq.security.domain.Security
import com.joohnq.security.domain.use_case.GetSecurityUseCase
import com.joohnq.security.domain.use_case.UpdateSecurityUseCase
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

    fun onIntent(intent: SecurityContract.Intent) {
        when (intent) {
            is SecurityContract.Intent.Get -> getSecurity()
            is SecurityContract.Intent.UpdateSecurity -> updateSecurity(intent.security)
        }
    }

    private fun getSecurity() = viewModelScope.launch {
        val res = getSecurityUseCase().toUiState()
        _state.update { it.copy(status = res) }
    }

    private fun updateSecurity(security: Security) = viewModelScope.launch {
        val res = updateSecurityUseCase(security).toUiState()
        res.onSuccess {
            _sideEffect.send(SecurityContract.SideEffect.SecurityUpdated)
        }.onFailure {
            _sideEffect.send(SecurityContract.SideEffect.ShowError(it))
        }

    }
}