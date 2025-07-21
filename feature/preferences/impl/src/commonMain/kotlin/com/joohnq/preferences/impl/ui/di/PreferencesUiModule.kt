package com.joohnq.preferences.impl.ui.di

import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val preferencesUiModule: Module = module {
    singleOf(::PreferencesViewModel)
}