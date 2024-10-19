package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val userPreferencesDAO: UserPreferencesDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ScreenModel {
    private val _userPreferences:
            MutableStateFlow<UiState<UserPreferences>> = MutableStateFlow(UiState.Idle)
    val userPreferences: MutableStateFlow<UiState<UserPreferences>> = _userPreferences

    fun getUserPreferences() =
        screenModelScope.launch(ioDispatcher) {
            _userPreferences.value = UiState.Loading
            userPreferencesDAO.getUserPreferences().catch {
                _userPreferences.value = UiState.Error(it.message.toString())
            }.collect {
                _userPreferences.value = UiState.Success(it)
            }
        }

    fun initUserPreferences() = screenModelScope.launch(ioDispatcher) {
        userPreferencesDAO.insertUserPreferences()
    }

    suspend fun setSkipWelcomeScreen(): Boolean = try {
        userPreferencesDAO.setSkipWelcomeScreen()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    suspend fun setSkipOnboardingScreen(): Boolean = try {
        userPreferencesDAO.setSkipOnboardingScreen()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }


    suspend fun setSkipGetUserNameScreen(): Boolean = try {
        userPreferencesDAO.setSkipGetUserNameScreen()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}