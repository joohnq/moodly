package com.joohnq.preferences.ui.di

import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val preferencesUiModule: Module = module {
    singleOf(::PreferencesViewModel)
}