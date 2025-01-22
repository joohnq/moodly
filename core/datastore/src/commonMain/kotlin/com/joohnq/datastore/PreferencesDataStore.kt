package com.joohnq.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect class PreferencesDataStore {
    fun init(): DataStore<Preferences>
}
