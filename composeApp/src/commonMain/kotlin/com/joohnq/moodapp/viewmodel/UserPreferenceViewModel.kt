package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.entities.UserPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val userPreferencesDAO: UserPreferencesDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userPreferences:
            MutableStateFlow<UserPreferences?> = MutableStateFlow(null)
    val userPreferences: MutableStateFlow<UserPreferences?> = _userPreferences

    fun getUserPreferences() {
        viewModelScope.launch(ioDispatcher)  {
            userPreferencesDAO.getUserPreferences().catch {
                it.printStackTrace()
            }.collect {
                _userPreferences.value = it
            }
        }
    }

    fun initUserPreferences() {
        viewModelScope.launch(ioDispatcher) {
            userPreferencesDAO.insertUserPreferences()
        }
    }

    fun setSkipWelcomeScreen() {
        viewModelScope.launch(ioDispatcher) {
            userPreferencesDAO.setSkipWelcomeScreen()
        }
    }
}