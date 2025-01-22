package com.joohnq.user.ui.viewmodel.user_preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipAuthUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipSecurityUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

            UserPreferenceIntent.ResetUpdating -> changeUpdatingStatus(UiState.Idle)
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
        changeAddingStatus(UiState.Loading)
        val res = addUserPreferencesUseCase(UserPreferences()).toUiState()
        changeAddingStatus(res)
    }

    private fun updateSkipWelcome(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipWelcomeUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun updateSkipOnboarding(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun updateSkipAuth(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipAuthUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun updateSkipSecurity(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipSecurityUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun changeUserPreferencesStatus(status: UiState<UserPreferences>) {
        _state.update { it.copy(userPreferences = status) }
    }

    private fun changeUpdatingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(updating = status) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }
}