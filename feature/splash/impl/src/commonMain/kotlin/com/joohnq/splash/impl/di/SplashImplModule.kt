package com.joohnq.splash.impl.di

import com.joohnq.splash.impl.ui.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashImplModule: Module =
    module {
        viewModel {
            SplashViewModel(
                addUserUseCase = get(),
                getSecurityUseCase = get(),
                getUserPreferencesUseCase = get()
            )
        }
    }
