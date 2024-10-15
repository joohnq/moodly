package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.UserPreferences
import com.joohnq.moodapp.model.flowGetTheOne
import com.joohnq.moodapp.view.state.UiState
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.TypedRealmObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val realm: Realm,
    private val ioDispatcher: CoroutineDispatcher
) : ScreenModel {
    private val _userPreferences:
            MutableStateFlow<UiState<UserPreferences>> = MutableStateFlow(UiState.Idle)
    val userPreferences: MutableStateFlow<UiState<UserPreferences>> = _userPreferences

    fun getUserPreferences() =
        screenModelScope.launch(ioDispatcher) {
            _userPreferences.value = UiState.Loading
            try {
                val userPreferences = realm.flowGetTheOne<UserPreferences>()
                    ?: throw Exception("No user preferences found")
                _userPreferences.value = UiState.Success(userPreferences)
            } catch (e: Exception) {
                _userPreferences.value = UiState.Error(e.message.toString())
            }
        }

    fun initUserPreferences() =
        screenModelScope.launch(ioDispatcher) {
            realm.write { copyToRealm(UserPreferences()) }
        }

    fun setSkipWelcomeScreen() =
        screenModelScope.launch(ioDispatcher) {
            try {
                val userPreferences = realm.flowGetTheOne<UserPreferences>()
                    ?: throw Exception("No user preferences found")
                realm.write {
                    findLatest(userPreferences)?.let { usePrefs ->
                        usePrefs.skipWelcomeScreen = true
                    }
                }
            } catch (e: Exception) {
                _userPreferences.value = UiState.Error(e.message.toString())
            }
            getUserPreferences()
        }

    fun setSkipOnboardingScreen() =
        screenModelScope.launch(ioDispatcher) {
            try {
                val userPreferences = realm.flowGetTheOne<UserPreferences>()
                    ?: throw Exception("No user preferences found")
                realm.write {
                    findLatest(userPreferences)?.let { usePrefs ->
                        usePrefs.skipOnboardingScreen = true
                    }
                }
            } catch (e: Exception) {
                _userPreferences.value = UiState.Error(e.message.toString())
            }
            getUserPreferences()
        }
}