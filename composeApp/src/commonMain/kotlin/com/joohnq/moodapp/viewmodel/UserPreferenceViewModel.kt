package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.UserPreferences
import com.joohnq.moodapp.model.repository.UserPreferencesRepository
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserPreferenceState(
    val userPreferences: UiState<UserPreferences> = UiState.Idle,
)

sealed class UserPreferenceIntent {
    data object AddUserPreferences : UserPreferenceIntent()
    data object GetUserPreferences : UserPreferenceIntent()
    data object LogoutUserPreferences : UserPreferenceIntent()

    data class UpdateSkipWelcomeScreen(
        val value: Boolean = true,
        val route: Screens = Screens.OnboardingGraph
    ) : UserPreferenceIntent()

    data class UpdateSkipOnboardingScreen(
        val value: Boolean = true,
        val route: Screens = Screens.GetUserNameScreen
    ) : UserPreferenceIntent()

    data class UpdateSkipGetUserNameScreen(
        val value: Boolean = true,
        val route: Screens = Screens.HomeGraph
    ) : UserPreferenceIntent()
}

sealed class UserPreferenceSideEffect {
    data class ShowToast(val message: String) : UserPreferenceSideEffect()
    data class NavigateTo(val route: Screens) : UserPreferenceSideEffect()
}

class UserPreferenceViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userPreferencesState:
            MutableStateFlow<UserPreferenceState> = MutableStateFlow(UserPreferenceState())
    val userPreferencesState: MutableStateFlow<UserPreferenceState> = _userPreferencesState

    private val _sideEffect = Channel<UserPreferenceSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: UserPreferenceIntent) {
        when (intent) {
            is UserPreferenceIntent.GetUserPreferences -> getUserPreferences()
            is UserPreferenceIntent.UpdateSkipGetUserNameScreen -> updateSkipGetUserNameScreen(
                route = intent.route,
                value = true
            )

            is UserPreferenceIntent.UpdateSkipOnboardingScreen -> updateSkipOnboardingScreen(
                route = intent.route,
                value = true
            )

            is UserPreferenceIntent.UpdateSkipWelcomeScreen -> setSkipWelcomeScreen(
                route = intent.route,
                value = true
            )

            UserPreferenceIntent.AddUserPreferences -> addUserPreferences()
            UserPreferenceIntent.LogoutUserPreferences -> {

            }

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
        if (!res)
            _sideEffect.send(
                UserPreferenceSideEffect.ShowToast(
                    "Failure add user preferences"
                )
            )
    }

    private fun setSkipWelcomeScreen(value: Boolean, route: Screens) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipWelcomeScreen(value)
            _sideEffect.send(
                if (res) UserPreferenceSideEffect.NavigateTo(route) else UserPreferenceSideEffect.ShowToast(
                    "Failure when skipping onboarding screen"
                )
            )
        }

    private fun updateSkipOnboardingScreen(value: Boolean, route: Screens) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipOnboardingScreen(value)

            _sideEffect.send(
                if (res) UserPreferenceSideEffect.NavigateTo(route) else UserPreferenceSideEffect.ShowToast(
                    "Failure when skipping onboarding screen"
                )
            )
        }

    private fun updateSkipGetUserNameScreen(value: Boolean, route: Screens) =
        viewModelScope.launch(dispatcher) {
            val res = userPreferencesRepository.updateSkipGetUserNameScreen(value)

            _sideEffect.send(
                if (res) UserPreferenceSideEffect.NavigateTo(route) else UserPreferenceSideEffect.ShowToast(
                    "Failure when skipping get user name screen"
                )
            )
        }
}