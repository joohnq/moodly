package com.joohnq.preferences.impl.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.use_case.GetUserPreferencesUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipAuthUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipWelcomeUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toUiState
import kotlinx.coroutines.launch

class PreferencesViewModel(
    initialState: PreferencesContract.State = PreferencesContract.State(),
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val updateSkipWelcomeUseCase: UpdateSkipWelcomeUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
    private val updateSkipAuthUseCase: UpdateSkipAuthUseCase,
    private val updateSkipSecurityUseCase: UpdateSkipSecurityUseCase,
) : BaseViewModel<PreferencesContract.State, PreferencesContract.Intent, PreferencesContract.SideEffect>(
    initialState = initialState
), PreferencesContract.ViewModel {

    override fun onIntent(intent: PreferencesContract.Intent) {
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
            updateState { it.copy(UiState.Loading) }
            val res = getUserPreferencesUseCase().toUiState()
            updateState { it.copy(res) }
        }

    private fun updateSkipWelcome(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipWelcomeUseCase(value).toUiState()
            res.onSuccess {
                emitEffect(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                emitEffect(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipOnboarding(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingUseCase(value).toUiState()
            res.onSuccess {
                emitEffect(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                emitEffect(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipAuth(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipAuthUseCase(value).toUiState()
            res.onSuccess {
                emitEffect(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                emitEffect(PreferencesContract.SideEffect.ShowError(it))
            }
        }

    private fun updateSkipSecurity(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipSecurityUseCase(value).toUiState()
            res.onSuccess {
                emitEffect(PreferencesContract.SideEffect.UpdatedPreferences)
            }.onFailure {
                emitEffect(PreferencesContract.SideEffect.ShowError(it))
            }
        }
}