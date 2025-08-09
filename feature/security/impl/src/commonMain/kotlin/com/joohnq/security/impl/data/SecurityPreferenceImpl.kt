package com.joohnq.security.impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.joohnq.security.api.Security
import com.joohnq.security.api.SecurityPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class SecurityPreferenceImpl(
    private val dataStore: DataStore<Preferences>,
) : SecurityPreference {
    companion object {
        private val SECURITY_KEY = stringPreferencesKey("SECURITY")
    }

    override suspend fun update(security: Security) {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                val string = Json.encodeToString(security)
                data[SECURITY_KEY] = string
            }
        }
    }

    override suspend fun get(): Security =
        withContext(Dispatchers.IO) {
            dataStore.data
                .map {
                    it.toMutablePreferences().getUserSecurityDecoded()
                }.first()
        }

    override suspend fun init() {
        withContext(Dispatchers.IO) {
            dataStore.edit { data ->
                val string = Json.encodeToString(Security.None)
                data[SECURITY_KEY] = string
            }
        }
    }

    private fun MutablePreferences.getUserSecurityDecoded(): Security =
        try {
            val security = this[SECURITY_KEY] ?: return Security.None

            Json.decodeFromString<Security>(security)
        } catch (_: Exception) {
            Security.Corrupted
        }
}
