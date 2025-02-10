package com.joohnq.preferences.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.preferences.domain.entity.AppPreferences
import com.joohnq.preferences.domain.use_case.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val updateSkipWelcomeUseCase: UpdateSkipWelcomeUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
    private val updateSkipAuthUseCase: UpdateSkipAuthUseCase,
    private val updateSkipSecurityUseCase: UpdateSkipSecurityUseCase,
) : ViewModel() {
    private val _state:
            MutableStateFlow<PreferenceState> =
        MutableStateFlow(PreferenceState())
    val state: MutableStateFlow<PreferenceState> = _state

    private val _sideEffect = Channel<PreferencesSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: PreferenceIntent) {
        when (intent) {
            is PreferenceIntent.GetPreferences -> getPreferences()
            is PreferenceIntent.UpdateSkipAuth ->
                updateSkipAuth(value = intent.value)

            is PreferenceIntent.UpdateSkipOnboarding ->
                updateSkipOnboarding(value = intent.value)

            is PreferenceIntent.UpdateSkipWelcome ->
                updateSkipWelcome(value = intent.value)

            is PreferenceIntent.UpdateSkipSecurity -> updateSkipSecurity(value = intent.value)
        }
    }

    private fun getPreferences() =
        viewModelScope.launch {
            changeUserPreferencesStatus(UiState.Loading)
            val res = getUserPreferencesUseCase().toUiState()
            changeUserPreferencesStatus(res)
        }

    private fun updateSkipWelcome(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipWelcomeUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesSideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipOnboarding(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesSideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipAuth(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipAuthUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesSideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipSecurity(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipSecurityUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesSideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun changeUserPreferencesStatus(status: UiState<AppPreferences>) {
        _state.update { it.copy(userPreferences = status) }
    }
}