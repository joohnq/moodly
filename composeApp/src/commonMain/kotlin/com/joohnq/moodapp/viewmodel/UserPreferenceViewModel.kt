package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.UserPreferencesRepository
import com.joohnq.moodapp.domain.UserPreferences
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserPreferenceState(
    val userPreferences: UiState<UserPreferences> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val updating: UiState<Boolean> = UiState.Idle
)

sealed class UserPreferenceIntent {
    data object AddUserPreferences : UserPreferenceIntent()
    data object GetUserPreferences : UserPreferenceIntent()
    data object LogoutUserPreferences : UserPreferenceIntent()
    data object ResetUpdating : UserPreferenceIntent()

    data class UpdateSkipWelcomeScreen(
        val value: Boolean = true,
    ) : UserPreferenceIntent()

    data class UpdateSkipOnboardingScreen(
        val value: Boolean = true,
    ) : UserPreferenceIntent()

    data class UpdateSkipGetUserNameScreen(
        val value: Boolean = true,
    ) : UserPreferenceIntent()
}

class UserPreferenceViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userPreferencesState:
            MutableStateFlow<UserPreferenceState> = MutableStateFlow(UserPreferenceState())
    val userPreferencesState: MutableStateFlow<UserPreferenceState> = _userPreferencesState

    fun onAction(intent: UserPreferenceIntent) {
        when (intent) {
            is UserPreferenceIntent.GetUserPreferences -> getUserPreferences()
            is UserPreferenceIntent.UpdateSkipGetUserNameScreen ->
                updateSkipGetUserNameScreen(value = true)

            is UserPreferenceIntent.UpdateSkipOnboardingScreen ->
                updateSkipOnboardingScreen(value = true)

            is UserPreferenceIntent.UpdateSkipWelcomeScreen ->
                updateSkipWelcomeScreen(value = true)

            UserPreferenceIntent.AddUserPreferences -> addUserPreferences()
            UserPreferenceIntent.LogoutUserPreferences -> {}

            UserPreferenceIntent.ResetUpdating -> resetUpdating()
        }
    }

    private fun getUserPreferences() =
        viewModelScope.launch(dispatcher) {
            _userPreferencesState.update {
                it.copy(userPreferences = UiState.Loading)
            }
            try {
                val res = userPreferencesRepository.getUserPreferences()
                _userPreferencesState.update {
                    it.copy(userPreferences = UiState.Success(res))
                }
            } catch (e: Exception) {
                _userPreferencesState.update {
                    it.copy(userPreferences = UiState.Error(e.message.toString()))
                }
            }
        }

    private fun addUserPreferences() = viewModelScope.launch(dispatcher) {
        val res = userPreferencesRepository.addUserPreferences(UserPreferences.init())

        _userPreferencesState.update {
            it.copy(adding = if (res) UiState.Success(res) else UiState.Error("Failure add user preferences"))
        }
    }

    private fun updateSkipWelcomeScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipWelcomeScreen(value)

            _userPreferencesState.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping onboarding screen"))
            }
        }

    private fun updateSkipOnboardingScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipOnboardingScreen(value)

            _userPreferencesState.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping onboarding screen"))
            }
        }

    private fun updateSkipGetUserNameScreen(value: Boolean) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipGetUserNameScreen(value)

            _userPreferencesState.update {
                it.copy(updating = if (res) UiState.Success(res) else UiState.Error("Failure when skipping get user name screen"))
            }
        }

    private fun resetUpdating() {
        _userPreferencesState.update {
            it.copy(updating = UiState.Idle)
        }
    }
}