package com.joohnq.preferences.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.preferences.api.use_case.GetUserPreferencesUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipAuthUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipWelcomeUseCase
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toUiState
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
            MutableStateFlow<PreferencesContract.State> =
        MutableStateFlow(PreferencesContract.State())
    val state: MutableStateFlow<PreferencesContract.State> = _state

    private val _sideEffect = Channel<PreferencesContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: PreferencesContract.Intent) {
        when (intent) {
            is PreferencesContract.Intent.GetPreferences -> getPreferences()
            is PreferencesContract.Intent.UpdateSkipAuth ->
                updateSkipAuth(value = intent.value)

            is PreferencesContract.Intent.UpdateSkipOnboarding ->
                updateSkipOnboarding(value = intent.value)

            is PreferencesContract.Intent.UpdateSkipWelcome ->
                updateSkipWelcome(value = intent.value)

            is PreferencesContract.Intent.UpdateSkipSecurity -> updateSkipSecurity(value = intent.value)
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
                _sideEffect.send(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipOnboarding(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipAuth(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipAuthUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipSecurity(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipSecurityUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                _sideEffect.send(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun changeUserPreferencesStatus(status: UiState<AppPreferences>) {
        _state.update { it.copy(userPreferences = status) }
    }
}