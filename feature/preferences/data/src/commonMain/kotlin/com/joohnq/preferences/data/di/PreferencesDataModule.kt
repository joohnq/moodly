package com.joohnq.preferences.data.di

import com.joohnq.preferences.data.repository.PreferencesRepositoryImpl
import com.joohnq.preferences.domain.repository.PreferencesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val preferencesDataModule = module {
    singleOf(::PreferencesRepositoryImpl) bind PreferencesRepository::class
}