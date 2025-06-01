package com.joohnq.splash.ui.di

import com.joohnq.splash.ui.viewmodel.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashUiModule: Module = module {
    viewModelOf(::SplashViewModel)
}