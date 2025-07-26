package com.joohnq.onboarding.impl.ui.di

import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val onboardingUiModule: Module = module {
    single<com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel> {
        _root_ide_package_.com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel()
    }
}