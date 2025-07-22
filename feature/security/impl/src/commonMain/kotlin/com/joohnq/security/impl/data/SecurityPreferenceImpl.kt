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

    override suspend fun update(security: Security): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                dataStore.edit { data ->
                    val string = Json.encodeToString(security)
                    data[SECURITY_KEY] = string
                }
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun get(): Result<Security> = withContext(Dispatchers.IO) {
        try {
            val item = dataStore.data.map {
                it.toMutablePreferences().getUserSecurityDecoded()
            }.first()
            Result.success(item)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun initUserSecurity(): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                dataStore.edit { data ->
                    val string = Json.encodeToString(Security.None)
                    data[SECURITY_KEY] = string
                }
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    private fun MutablePreferences.getUserSecurityDecoded(): Security {
        val security = this[SECURITY_KEY] ?: return Security.None
        return try {
            Json.decodeFromString<Security>(security)
        } catch (e: Exception) {
            Security.Corrupted
        }
    }
}
