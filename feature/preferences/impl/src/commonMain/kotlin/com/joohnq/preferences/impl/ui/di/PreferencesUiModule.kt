package com.joohnq.preferences.impl.ui.di

import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val preferencesUiModule: Module =
    module {
        single {
            PreferencesViewModel(
                getUserPreferencesUseCase = get(),
                updateSkipWelcomeUseCase = get(),
                updateSkipOnboardingUseCase = get(),
                updateSkipAuthUseCase = get(),
                updateSkipSecurityUseCase = get()
            )
        }
    }
