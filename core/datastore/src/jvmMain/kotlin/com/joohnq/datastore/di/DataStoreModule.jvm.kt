package com.joohnq.datastore.di

import com.joohnq.datastore.PreferencesDataStore
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val dataStoreModule: Module =
    module {
        singleOf(::PreferencesDataStore)
        singleOf(PreferencesDataStore::init)
    }
