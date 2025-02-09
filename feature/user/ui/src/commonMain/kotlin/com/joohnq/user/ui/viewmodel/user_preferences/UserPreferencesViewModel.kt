package com.joohnq.user.ui.viewmodel.user_preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.use_case.user_preferences.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UserPreferencesSideEffect {
    data class ShowError(val message: String) : UserPreferencesSideEffect
    data object AddedUserPreferences : UserPreferencesSideEffect
    data object UpdatedUserPreferences : UserPreferencesSideEffect
}

class UserPreferencesViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val addUserPreferencesUseCase: AddUserPreferencesUseCase,
    private val updateSkipWelcomeUseCase: UpdateSkipWelcomeUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
    private val updateSkipAuthUseCase: UpdateSkipAuthUseCase,
    private val updateSkipSecurityUseCase: UpdateSkipSecurityUseCase,
) : ViewModel() {
    private val _state:
            MutableStateFlow<UserPreferenceState> =
        MutableStateFlow(UserPreferenceState())
    val state: MutableStateFlow<UserPreferenceState> = _state

    private val _sideEffect = Channel<UserPreferencesSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: UserPreferenceIntent) {
        when (intent) {
            is UserPreferenceIntent.GetUserPreferences -> getUserPreferences()
            is UserPreferenceIntent.UpdateSkipAuth ->
                updateSkipAuth(value = true)

            is UserPreferenceIntent.UpdateSkipOnboarding ->
                updateSkipOnboarding(value = true)

            is UserPreferenceIntent.UpdateSkipWelcome ->
                updateSkipWelcome(value = true)

            UserPreferenceIntent.AddUserPreferences -> addUserPreferences()

            is UserPreferenceIntent.UpdateSkipSecurity -> updateSkipSecurity(value = true)
        }
    }

    private fun getUserPreferences() =
        viewModelScope.launch {
            changeUserPreferencesStatus(UiState.Loading)
            val res = getUserPreferencesUseCase().toUiState()
            changeUserPreferencesStatus(res)
        }

    private fun addUserPreferences() = viewModelScope.launch {
        val res = addUserPreferencesUseCase(UserPreferences()).toUiState()
        res.onSuccess {
            _sideEffect.send(UserPreferencesSideEffect.AddedUserPreferences)
        }.onFailure {
            _sideEffect.send(UserPreferencesSideEffect.ShowError(it.message.toString()))
        }
    }

    private fun updateSkipWelcome(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipWelcomeUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(UserPreferencesSideEffect.UpdatedUserPreferences)
            }.onFailure {
                _sideEffect.send(UserPreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipOnboarding(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(UserPreferencesSideEffect.UpdatedUserPreferences)
            }.onFailure {
                _sideEffect.send(UserPreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipAuth(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipAuthUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(UserPreferencesSideEffect.UpdatedUserPreferences)
            }.onFailure {
                _sideEffect.send(UserPreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun updateSkipSecurity(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipSecurityUseCase(value).toUiState()
            res.onSuccess {
                _sideEffect.send(UserPreferencesSideEffect.UpdatedUserPreferences)
            }.onFailure {
                _sideEffect.send(UserPreferencesSideEffect.ShowError(it.message.toString()))
            }
        }

    private fun changeUserPreferencesStatus(status: UiState<UserPreferences>) {
        _state.update { it.copy(userPreferences = status) }
    }
}