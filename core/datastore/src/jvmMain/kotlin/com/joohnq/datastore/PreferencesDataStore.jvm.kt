package com.joohnq.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

actual class PreferencesDataStore {
    actual fun init(): DataStore<Preferences> =
        createDataStore(
            producePath = {
                val file = File(System.getProperty("java.io.tmpdir"), DATASTORE_FILE_NAME)
                file.absolutePath
            }
        )
}
