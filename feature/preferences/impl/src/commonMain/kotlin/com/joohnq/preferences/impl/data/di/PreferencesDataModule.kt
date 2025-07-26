package com.joohnq.preferences.impl.data.di

import com.joohnq.preferences.api.repository.PreferencesRepository
import com.joohnq.preferences.impl.data.repository.PreferencesRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val preferencesDataModule =
    module {
        singleOf(::PreferencesRepositoryImpl) bind PreferencesRepository::class
    }
