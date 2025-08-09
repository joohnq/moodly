package com.joohnq.preferences.impl.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.joohnq.preferences.api.entity.AppPreferences
import com.joohnq.preferences.api.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferencesRepository {
    companion object {
        private val SKIP_WELCOME_KEY = stringPreferencesKey("SKIP_WELCOME")
        private val SKIP_ONBOARDING_KEY = stringPreferencesKey("SKIP_ONBOARDING")
        private val SKIP_AUTH_KEY = stringPreferencesKey("SKIP_AUTH")
        private val SKIP_SECURITY_KEY = stringPreferencesKey("SKIP_SECURITY")
    }

    override suspend fun getUserPreferences(): AppPreferences? =
        withContext(Dispatchers.IO) {
            dataStore.data
                .map { preferences ->
                    preferences.toMutablePreferences().toAppPreferences()
                }.first()
        }

    override suspend fun updateSkipWelcome(value: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                data[SKIP_WELCOME_KEY] = value.toString()
            }
        }
    }

    override suspend fun updateSkipOnboarding(value: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                data[SKIP_ONBOARDING_KEY] = value.toString()
            }
        }
    }

    override suspend fun updateSkipAuth(value: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                data[SKIP_AUTH_KEY] = value.toString()
            }
        }
    }

    override suspend fun updateSkipSecurity(value: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                data[SKIP_SECURITY_KEY] = value.toString()
            }
        }
    }

    private fun MutablePreferences.toAppPreferences(): AppPreferences {
        val skipWelcome = this[SKIP_WELCOME_KEY]?.toBooleanStrictOrNull() ?: false
        val skipOnboarding = this[SKIP_ONBOARDING_KEY]?.toBooleanStrictOrNull() ?: false
        val skipAuth = this[SKIP_AUTH_KEY]?.toBooleanStrictOrNull() ?: false
        val skipSecurity = this[SKIP_SECURITY_KEY]?.toBooleanStrictOrNull() ?: false

        return AppPreferences(
            skipWelcome = skipWelcome,
            skipOnboarding = skipOnboarding,
            skipAuth = skipAuth,
            skipSecurity = skipSecurity
        )
    }
}
