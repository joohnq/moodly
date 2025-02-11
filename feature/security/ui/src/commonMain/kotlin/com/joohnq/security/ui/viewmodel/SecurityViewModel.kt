package com.joohnq.security.ui.viewmodel

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
    private val _state: MutableStateFlow<SecurityState> =
        MutableStateFlow(SecurityState())
    val state: StateFlow<SecurityState> = _state

    private val _sideEffect = Channel<SecuritySideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: SecurityIntent) {
        when (intent) {
            is SecurityIntent.GetSecurity -> getSecurity()
            is SecurityIntent.UpdateSecurity -> updateSecurity(intent.security)
        }
    }

    private fun getSecurity() = viewModelScope.launch {
        val res = getSecurityUseCase().toUiState()
        _state.update { it.copy(item = res) }
    }

    private fun updateSecurity(security: Security) = viewModelScope.launch {
        val res = updateSecurityUseCase(security).toUiState()
        res.onSuccess {
            _sideEffect.send(SecuritySideEffect.OnSecurityUpdated)
        }.onFailure {
            _sideEffect.send(SecuritySideEffect.ShowError(it))
        }

    }
}