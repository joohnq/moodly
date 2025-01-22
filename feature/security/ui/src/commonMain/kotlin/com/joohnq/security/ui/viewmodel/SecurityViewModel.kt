package com.joohnq.security.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.security.domain.Security
import com.joohnq.security.domain.SecurityPreference
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SecurityViewModel(
    private val securityPreference: SecurityPreference,
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
        val res = securityPreference.get().toUiState()
        _state.update { it.copy(item = res) }
    }

    private fun updateSecurity(security: Security) = viewModelScope.launch {
        val res = securityPreference.update(security).toUiState()
        res.onSuccess {
            _sideEffect.send(SecuritySideEffect.OnSecurityUpdated)
        }.onFailure {
            _sideEffect.send(SecuritySideEffect.ShowError(it))
        }

    }
}