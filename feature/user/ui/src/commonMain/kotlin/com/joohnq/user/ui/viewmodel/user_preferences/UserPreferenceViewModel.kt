package com.joohnq.user.ui.viewmodel.user_preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipGetUserNameScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeScreenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val addUserPreferencesUseCase: AddUserPreferencesUseCase,
    private val updateSkipWelcomeScreenUseCase: UpdateSkipWelcomeScreenUseCase,
    private val updateSkipOnboardingScreenUseCase: UpdateSkipOnboardingScreenUseCase,
    private val updateSkipGetUserNameScreenUseCase: UpdateSkipGetUserNameScreenUseCase,
) : ViewModel() {
    private val _state:
            MutableStateFlow<UserPreferenceViewModelState> =
        MutableStateFlow(UserPreferenceViewModelState())
    val state: MutableStateFlow<UserPreferenceViewModelState> = _state

    fun onAction(intent: UserPreferenceViewModelIntent) {
        when (intent) {
            is UserPreferenceViewModelIntent.GetUserPreferences -> getUserPreferences()
            is UserPreferenceViewModelIntent.UpdateSkipGetUserNameScreen ->
                updateSkipGetUserNameScreen(value = true)

            is UserPreferenceViewModelIntent.UpdateSkipOnboardingScreen ->
                updateSkipOnboardingScreen(value = true)

            is UserPreferenceViewModelIntent.UpdateSkipWelcomeScreen ->
                updateSkipWelcomeScreen(value = true)

            UserPreferenceViewModelIntent.AddUserPreferences -> addUserPreferences()
            UserPreferenceViewModelIntent.LogoutUserPreferences -> {}

            UserPreferenceViewModelIntent.ResetUpdating -> changeUpdatingStatus(UiState.Idle)
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

    private fun updateSkipWelcomeScreen(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipWelcomeScreenUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun updateSkipOnboardingScreen(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipOnboardingScreenUseCase(value).toUiState()
            changeUpdatingStatus(res)
        }

    private fun updateSkipGetUserNameScreen(value: Boolean) =
        viewModelScope.launch {
            val res = updateSkipGetUserNameScreenUseCase(value).toUiState()
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