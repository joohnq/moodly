package com.joohnq.user.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipGetUserNameScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeScreenUseCase
import com.joohnq.mood.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val addUserPreferencesUseCase: AddUserPreferencesUseCase,
    private val updateSkipWelcomeScreenUseCase: UpdateSkipWelcomeScreenUseCase,
    private val updateSkipOnboardingScreenUseCase: UpdateSkipOnboardingScreenUseCase,
    private val updateSkipGetUserNameScreenUseCase: UpdateSkipGetUserNameScreenUseCase,
    private val dispatcher: CoroutineDispatcher
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

            UserPreferenceViewModelIntent.ResetUpdating -> resetUpdating()
        }
    }

    private fun getUserPreferences() =
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(userPreferences = UiState.Loading)
            }
            try {
                val res = getUserPreferencesUseCase()
                _state.update {
                    it.copy(userPreferences = UiState.Success(res))
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(userPreferences = UiState.Error(e.message.toString()))
                }
            }
        }

    private fun addUserPreferences() = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val res = addUserPreferencesUseCase(UserPreferences.init())

        changeAddingStatus(if (res) UiState.Success(true) else UiState.Error("Failure add user preferences"))
    }

    private fun updateSkipWelcomeScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = updateSkipWelcomeScreenUseCase(value)

            _state.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping onboarding screen"))
            }
        }

    private fun updateSkipOnboardingScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = updateSkipOnboardingScreenUseCase(value)

            _state.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping onboarding screen"))
            }
        }

    private fun updateSkipGetUserNameScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = updateSkipGetUserNameScreenUseCase(value)

            _state.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping get user name screen"))
            }
        }

    private fun resetUpdating() {
        _state.update {
            it.copy(updating = UiState.Idle)
        }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }
}