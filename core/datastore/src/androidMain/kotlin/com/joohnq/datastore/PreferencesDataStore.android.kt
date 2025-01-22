package com.joohnq.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual class PreferencesDataStore(private val context: Context) {
    actual fun init(): DataStore<Preferences> = createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )
}