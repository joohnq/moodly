package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.view.state.UiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val userPreferencesDAO: UserPreferencesDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userPreferences:
            MutableStateFlow<UiState<UserPreferences>> = MutableStateFlow(UiState.Idle)
    val userPreferences: MutableStateFlow<UiState<UserPreferences>> = _userPreferences

    /*
   * Get the user preferences from database
   *  Tested
   * */
    fun getUserPreferences() =
        viewModelScope.launch(ioDispatcher) {
            _userPreferences.value = UiState.Loading
            userPreferencesDAO.getUserPreferences().catch {
                _userPreferences.value = UiState.Error(it.message.toString())
            }.collect {
                _userPreferences.value = UiState.Success(it)
            }
        }

    /*
   * Initialize the user preferences database when firstly launch the app
   * Tested
   * */
    suspend fun initUserPreferences(): Boolean =
        executeWithBoolean(userPreferencesDAO::insertUserPreferences)

    /*
   * Set the skip welcome screen to true when already pass from the welcome screens flow
   * Tested
   * */
    suspend fun setSkipWelcomeScreen(): Boolean =
        executeWithBoolean(userPreferencesDAO::setSkipWelcomeScreen)

    /*
  * Set the skip onboarding screen to true when already pass from the onboarding screens flow
  * Tested
  * */
    suspend fun setSkipOnboardingScreen(): Boolean =
        executeWithBoolean(userPreferencesDAO::setSkipOnboardingScreen)

    /*
  * Set the skip get user screen to true when already get the user name
  * Tested
  * */
    suspend fun setSkipGetUserNameScreen(): Boolean =
        executeWithBoolean(userPreferencesDAO::setSkipGetUserNameScreen)

}